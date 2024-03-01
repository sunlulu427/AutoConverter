package com.mato.stg4.generator

import com.mato.stg4.annotation.NamingStrategy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals


/**
 * @date: 2024-03-01
 * @author: 孙路路 sunlulu.tomato
 */
class JsonNamingStrategyTest {

    @ParameterizedTest
    @ValueSource(strings = ["name", "age", "userCount", "user_count"])
    fun `test strategy none`(name: String) {
        assertEquals(NamingStrategy.None.resolve(name), name)
    }

    @Test
    fun `test camel to underline`() {
        val pairs = listOf(
            "name" to "name",
            "enterUgController" to "enter_ug_controller",
            "userCount" to "user_count",
            "symbolProcessorEnvironment" to "symbol_processor_environment"
        )
        for (pair in pairs) {
            assertEquals(pair.second, NamingStrategy.CamelToUnderline.resolve(pair.first))
        }
    }
}