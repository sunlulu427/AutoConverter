package com.mato.stg4.annotation

/**
 */
/**
 * Auto convert

 * @date: 2024-02-29
 * @author: 孙路路 sunlulu.tomato
 * @property functions
 * @property namingStrategy
 * @property skipNulls
 * @property filePostfix
 * @constructor Create empty Auto convert
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class AutoConvert(
    val functions: Array<STGFunction> = [],
    val namingStrategy: NamingStrategy = NamingStrategy.None,
    val skipNulls: Boolean = true,
    val filePostfix: String = "Ext"
)
