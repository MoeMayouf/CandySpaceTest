package com.mayouf.data.datastore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mayouf.data.api.StackExchangeApiService
import com.mayouf.data.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runBlockingTest
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


@RunWith(MockitoJUnitRunner::class)
class StackExchangeRemoteDataStoreImplTest {
    private lateinit var cut: StackExchangeRemoteDataStore

    @Mock
    lateinit var apiService: StackExchangeApiService

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @FlowPreview
    @Before
    fun setUp() {
        cut = StackExchangeRemoteDataStoreImpl(apiService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getUsers then apiService invoked`() {
        runBlockingTest {
            // When
            whenever(apiService.getUsers("", "", "")).thenReturn(mock())

            cut.getStackExchangeUsers("", "", "")

            // Then
            verify(apiService, times(1)).getUsers("", "", "")
        }
    }
}
