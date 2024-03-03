package com.mato.stg4cpp

import com.mato.stg4.annotation.AutoConvert
import com.mato.stg4cpp.pkg2.Gender
import com.mato.stg4cpp.pkg2.Location

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/31
 */
@AutoConvert
data class Restaurant(
    val score: Float = 0.0f,
    val comments: Int = 0,
    val boss: Boss,
    val location: Location? = null,
    val foods: List<String> = emptyList(),
    val gender: Gender = Gender()
)


@AutoConvert
data class Boss(
    val gender: Int,
    val age: Int,
    val name: String
)