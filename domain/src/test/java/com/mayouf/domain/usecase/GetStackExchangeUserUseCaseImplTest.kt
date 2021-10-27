package com.mayouf.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mayouf.data.utils.MainCoroutineRule
import com.mayouf.domain.entities.DomainBadgeCounts
import com.mayouf.domain.entities.DomainItems
import com.mayouf.domain.entities.DomainStackExchange
import com.mayouf.domain.repository.StackExchangeRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@FlowPreview
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetStackExchangeUserUseCaseImplTest {
    private lateinit var cut: GetStackExchangeUserUseCase

    @Mock
    lateinit var repository: StackExchangeRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        cut = GetStackExchangeUserUseCaseImpl(repository)
    }

    @ObsoleteCoroutinesApi
    @Test
    fun `when execute then returns expected DomainStackExchange`() {
        runBlocking {
            val domainStackExchange = DomainStackExchange(
                listOf(DomainItems(DomainBadgeCounts(1, 1, 1), 1, 1, 1, "1", "1", "1")),
                true, 1, 1
            )
            val channelCompanyInfo = ConflatedBroadcastChannel<DomainStackExchange>()
            channelCompanyInfo.trySend(domainStackExchange).isSuccess
            val expected = DomainStackExchange(
                listOf(DomainItems(DomainBadgeCounts(1, 1, 1), 1, 1, 1, "1", "1", "1")),
                true, 1, 1
            )
            whenever(
                repository.getStackExchangeUsers(
                    "",
                    "",
                    ""
                )
            ).thenReturn(channelCompanyInfo.asFlow())

            val actualValue = cut.execute("", "", "").first()
            verify(repository, times(1)).getStackExchangeUsers("", "", "")
            assertEquals(expected, actualValue)
        }
    }


}