package com.mayouf.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayouf.domain.entities.DomainStackExchange
import com.mayouf.domain.usecase.GetStackExchangeUserUseCase
import com.mayouf.presentation.mapper.DomainStackExchangeToUiStackExchangeMapper
import com.mayouf.presentation.model.UiStackExchange
import com.mayouf.presentation.utils.Event
import com.mayouf.presentation.utils.eventOf
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

abstract class StackExchangeUsersViewModel : ViewModel() {
    abstract fun stackExchangeUsers(order: String, site: String, name: String)

    abstract val stackExchangeUsers: LiveData<UiStackExchange>
    abstract val loadingStackExchangeUsers: LiveData<Boolean>
    abstract val errorStackExchangeUsers: LiveData<Event<Unit>>
}

class StackExchangeUsersViewModelImpl @Inject constructor(
    private val getStackExchangeUserUseCase: GetStackExchangeUserUseCase,
    private val domainToUiMapper: DomainStackExchangeToUiStackExchangeMapper
) : StackExchangeUsersViewModel() {

    private val _loadingSTackExchangeUsers = MediatorLiveData<Boolean>()
    private val _stackExchangeUsers = MediatorLiveData<UiStackExchange>()
    private val _errorStackExchangeUsers = MediatorLiveData<Event<Unit>>()

    override val loadingStackExchangeUsers: LiveData<Boolean>
        get() = _loadingSTackExchangeUsers
    override val stackExchangeUsers: LiveData<UiStackExchange>
        get() = _stackExchangeUsers
    override val errorStackExchangeUsers: LiveData<Event<Unit>>
        get() = _errorStackExchangeUsers

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _loadingSTackExchangeUsers.value = false
    }

    override fun stackExchangeUsers(order: String, site: String, name: String) {
        viewModelScope.launch(errorHandler) {
            _loadingSTackExchangeUsers.value = true
            try {
                getStackExchangeUserUseCase.execute(order, site, name)
                    .catch { throwable -> handleExceptions(throwable) }
                    .collect { value: DomainStackExchange ->
                        Log.i("Something", "Check8")
                        val uiModel = domainToUiMapper.toUiModel(value)
                        _stackExchangeUsers.value = uiModel
                        _loadingSTackExchangeUsers.value = false
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun handleExceptions(throwable: Throwable) {
        Log.e("Throwable", throwable.stackTraceToString())
        _errorStackExchangeUsers.postValue(eventOf(Unit))
        _loadingSTackExchangeUsers.value = false
    }


}