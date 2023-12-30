package com.mato.stg4.generator.graph

/**
 * @Author sunlulu.tomato
 * @Date 2024/1/2
 */
interface Graph<NODE : Any> {
    val isDirected: Boolean
    fun degree(): Int
    fun inDegree(node: NODE): Int
    fun outDegree(node: NODE): Int
    fun nodes(): Set<NODE>
}