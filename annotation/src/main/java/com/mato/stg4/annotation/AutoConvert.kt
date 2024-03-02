package com.mato.stg4.annotation

/**
 * Auto convert annotation

 * @property functions enabled auto convert functions, toJSONObject by default
 * @property namingStrategy naming strategy, None by default
 * @property filePostfix file postfix, Ext by default, filename is ClassName + filePostfix
 * @constructor Create empty Auto convert
 */
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class AutoConvert(
    val functions: Array<ACFunction> = [ACFunction.ToJSONObject],
    val namingStrategy: NamingStrategy = NamingStrategy.None,
    val filePostfix: String = "Ext"
)
