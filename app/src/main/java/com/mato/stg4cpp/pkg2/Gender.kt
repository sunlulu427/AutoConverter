package com.mato.stg4cpp.pkg2

import com.mato.stg4.annotation.AutoConvert
import kotlin.random.Random

/**
 * @date: 2024-03-02
 * @author: 孙路路 sunlulu.tomato
 */
@AutoConvert
data class Gender(
    val value: Int = Random.nextInt(),
    val desc: String = "gender"
)