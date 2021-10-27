package com.mayouf.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mayouf.data.datastore.StackExchangeRemoteDataStore
import com.mayouf.data.mapper.DataStackExchangeToDomainStackExchangeMapper
import com.mayouf.data.respository.StackExchangeRepositoryImpl
import com.mayouf.data.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StackExchangeRepositoryImplTest {

    private lateinit var cut: StackExchangeRepositoryImpl

    @Mock
    lateinit var remoteDataStore: StackExchangeRemoteDataStore

    @Mock
    lateinit var dataToDomainMapper: DataStackExchangeToDomainStackExchangeMapper

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        cut = StackExchangeRepositoryImpl(remoteDataStore, dataToDomainMapper)
    }

    @Test
    fun `when getUsers then remoteDataStore invoked`() {
        runBlockingTest {
            // When
            whenever(remoteDataStore.getStackExchangeUsers("", "", "")).thenReturn(mock())

            cut.getStackExchangeUsers("", "", "")

            // Then
            verify(remoteDataStore, times(1)).getStackExchangeUsers("", "", "")
        }
    }
}