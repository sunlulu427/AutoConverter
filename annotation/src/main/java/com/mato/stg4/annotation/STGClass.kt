package com.mato.stg4.annotation

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/29
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class STGClass(val path: String = "")
