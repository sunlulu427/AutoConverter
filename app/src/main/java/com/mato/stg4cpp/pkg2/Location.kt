package com.mato.stg4cpp.pkg2

import com.mato.stg4.annotation.AutoConvert

/**
 * @date: 2024-03-03
 * @author: 孙路路 sunlulu.tomato
 */
@AutoConvert
data class Location(
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val address: String = ""
)