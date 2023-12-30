package com.mato.stg4.generator

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/31
 */
enum class STGHeaderType {
    PRAGMA_ONCE,
    IF_NOE_DEFINE
}

sealed interface STGHeader {
    val begin: String
    val end: String get() = ""

    data object PragmaOnce : STGHeader {
        override val begin: String
            get() = "#pragma once"
    }

    data class IfNotDefine(private val tag: String) : STGHeader {
        override val begin: String
            get() = """
                #ifndef __${this.tag.uppercase()}_H__
                #define __${this.tag.uppercase()}_H__
            """.trimIndent()

        override val end: String
            get() = "#endif"
    }
}