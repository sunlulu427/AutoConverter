package com.mato.stg4.annotation

/**
 * @date: 2024-02-29
 * @author: 孙路路 sunlulu.tomato
 *
 * Supported auto convert functions
 *
 * @property ToJSONObject generate toJSONObject function for annotated classes
 * @property FromJSONObject generate fromJSONObject function for annotated classes
 */
enum class ACFunction {
    ToJSONObject,
    FromJSONObject
}