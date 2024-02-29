package com.mato.stg4cpp

import com.mato.stg4.annotation.AutoConvert

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/31
 */
@AutoConvert
data class Restaurant(
    val boss: Boss,
    val location: String = "",
    val score: Float = 0.0f,
    val comments: Int = 0,
    val staffs: Int = 0,
    val foods: List<String> = emptyList()
)


@AutoConvert
data class Boss(
    val gender: Int,
    val age: Int,
    val name: String
)