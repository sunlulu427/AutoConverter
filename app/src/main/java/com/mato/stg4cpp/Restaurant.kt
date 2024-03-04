package com.mato.stg4cpp

import com.mato.stg4.annotation.ACFunction
import com.mato.stg4.annotation.AutoConvert
import com.mato.stg4cpp.pkg2.Location

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/31
 */
@AutoConvert(functions = [ACFunction.FromJSONObject, ACFunction.ToJSONObject])
data class Restaurant(
    val score: Float,
    val comments: Int = 0,
    val location: Location? = null,
    val foods: List<String> = emptyList(),
)