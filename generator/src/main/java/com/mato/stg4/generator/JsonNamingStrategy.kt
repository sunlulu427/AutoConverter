package com.mato.stg4.generator

import com.mato.stg4.annotation.NamingStrategy

/**
 * @date: 2024-03-01
 * @author: 孙路路 sunlulu.tomato
 */
private val camelStyle = Regex("([a-z])([A-Z]+)")

fun NamingStrategy.resolve(name: String) = when (this) {
    NamingStrategy.None -> name
    NamingStrategy.CamelToUnderline -> name.replace(camelStyle, "$1_$2").lowercase()
}