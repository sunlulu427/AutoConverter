package com.mato.stg4.generator

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import kotlin.reflect.KClass

/**
 * @date: 2024-03-01
 * @author: 孙路路 sunlulu.tomato
 */
class MatchingClassSet<T : Any>(
    private val kClass: KClass<T>,
    private val resolver: Resolver
) {
    private val symbols = resolver
        .getSymbolsWithAnnotation(kClass.java.name)
        .filterIsInstance<KSClassDeclaration>()

    private val qualifiedNames = symbols.map { it.qualifiedName }


}