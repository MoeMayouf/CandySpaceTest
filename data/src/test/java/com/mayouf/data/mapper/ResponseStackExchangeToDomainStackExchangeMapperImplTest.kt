package com.mayouf.data.mapper

import com.mayouf.data.entities.data.DataStackExchange
import com.mayouf.domain.entities.DomainStackExchange
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ResponseStackExchangeToDomainStackExchangeMapperImplTest(
    private val dataStackExchange: DataStackExchange,
    private val domainStackExchange: DomainStackExchange
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    DataStackExchange(
                        mutableListOf(),
                        true,
                        1,
                        10
                    ), DomainStackExchange(
                        mutableListOf(),
                        true,
                        1,
                        10
                    )
                ), arrayOf(
                    DataStackExchange(
                        mutableListOf(),
                        true,
                        1,
                        1000000
                    ), DomainStackExchange(
                        mutableListOf(),
                        true,
                        1,
                        1000000
                    )
                ), arrayOf(
                    DataStackExchange(
                        mutableListOf(),
                        true,
                        0,
                        0
                    ), DomainStackExchange(
                        mutableListOf(),
                        true,
                        0,
                        0
                    )

                )
            )
        }
    }

    private lateinit var cut: DataStackExchangeToDomainStackExchangeMapperImpl

    @Before
    fun setUp() {
        cut = DataStackExchangeToDomainStackExchangeMapperImpl()
    }

    @Test
    fun `Given dataStackExchange when toRepositoryModel then returns domainStackExchange`() {
        // When
        val actualValue = cut.toDomainModel(dataStackExchange)

        // Then
        assertEquals(domainStackExchange, actualValue)
    }


}