package com.mayouf.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mayouf.domain.entities.DomainStackExchange
import com.mayouf.domain.usecase.GetStackExchangeUserUseCase
import com.mayouf.presentation.mapper.DomainStackExchangeToUiStackExchangeMapper
import com.mayouf.presentation.model.UiStackExchange
import com.mayouf.presentation.viewmodel.StackExchangeUsersViewModel
import com.mayouf.presentation.viewmodel.StackExchangeUsersViewModelImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StackExchangeUsersViewModelTest {
    private lateinit var cut: StackExchangeUsersViewModel

    @Mock
    lateinit var getStackExchangeUserUseCase: GetStackExchangeUserUseCase

    @Mock
    lateinit var mapper: DomainStackExchangeToUiStackExchangeMapper

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        cut = StackExchangeUsersViewModelImpl(getStackExchangeUserUseCase, mapper)
    }

    @Test
    fun `When launches then UiStackExchange with expected result`() {
        runBlockingTest {
            // Given
            val launchUiModelRetrievedTestObserver = mock<Observer<UiStackExchange>>()
            cut.stackExchangeUsers.observeForever(launchUiModelRetrievedTestObserver)
            val stackExchangeUsers =
                DomainStackExchange(
                    mutableListOf(),
                    true, 1, 1
                )
            val expected =
                UiStackExchange(
                    mutableListOf(),
                    true, 1, 1
                )
            val flow = flow {
                emit(stackExchangeUsers)
            }
            whenever(
                getStackExchangeUserUseCase.execute("", "", "")
            ).thenReturn(flow)
            whenever(mapper.toUiModel(stackExchangeUsers)).thenReturn(expected)

            // When
            cut.stackExchangeUsers("", "", "")
            val actualValue = cut.stackExchangeUsers.value

            // Then
            verify(getStackExchangeUserUseCase, times(1)).execute("", "", "")
            assertEquals(expected, actualValue)
        }
    }
}