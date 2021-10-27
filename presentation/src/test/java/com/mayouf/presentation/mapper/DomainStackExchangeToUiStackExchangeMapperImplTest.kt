package com.mayouf.presentation.mapper

import com.mayouf.domain.entities.DomainStackExchange
import com.mayouf.presentation.model.UiStackExchange
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DomainStackExchangeToUiStackExchangeMapperImplTest(
    private val domainStackExchange: DomainStackExchange,
    private val uiStackExchange: UiStackExchange
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    DomainStackExchange(
                        mutableListOf(),
                        true,
                        1,
                        10
                    ), UiStackExchange(
                        mutableListOf(),
                        true,
                        1,
                        10
                    )
                ), arrayOf(
                    DomainStackExchange(
                        mutableListOf(),
                        true,
                        1,
                        1000000
                    ), UiStackExchange(
                        mutableListOf(),
                        true,
                        1,
                        1000000
                    )
                ), arrayOf(
                    DomainStackExchange(
                        mutableListOf(),
                        true,
                        0,
                        0
                    ), UiStackExchange(
                        mutableListOf(),
                        true,
                        0,
                        0
                    )

                )
            )
        }
    }

    private lateinit var cut: DomainStackExchangeToUiStackExchangeMapperImpl

    @Before
    fun setUp() {
        cut = DomainStackExchangeToUiStackExchangeMapperImpl()
    }

    @Test
    fun `Given domainStackExchange when toUiModel then return UiStackExchange`() {
        val actualValue = cut.toUiModel(domainStackExchange)

        assertEquals(uiStackExchange, actualValue)
    }
}
