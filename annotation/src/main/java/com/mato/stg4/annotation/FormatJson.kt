package com.mato.stg4.annotation

/**
 * @date: 2024-02-29
 * @author: 孙路路 sunlulu.tomato
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class FormatJson(
    val postfix: String = "Ext"
)
