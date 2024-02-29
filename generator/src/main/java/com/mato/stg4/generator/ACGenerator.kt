package com.mato.stg4.generator

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration

/**
 * @date: 2024-02-29
 * @author: 孙路路 sunlulu.tomato
 */
abstract class ACGenerator(
    private val env: SymbolProcessorEnvironment,
    private val resolver: Resolver,
    private val ksClass: KSClassDeclaration,
) {
    abstract val functionName: String

}