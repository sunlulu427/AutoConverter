package com.mato.stg4.annotation

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/29
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class STGField(val name: String = "")
