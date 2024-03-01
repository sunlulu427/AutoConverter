package com.mato.stg4.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.mato.stg4.annotation.AutoConvert

/**
 * @date: 2024-02-29
 * @author: 孙路路 sunlulu.tomato
 */
@OptIn(KspExperimental::class)
abstract class ACGenerator(
    private val env: SymbolProcessorEnvironment,
    private val resolver: Resolver,
    private val ksClass: KSClassDeclaration,
) {

    private val className = ksClass.simpleName.asString()
    private val packageName = ksClass.packageName.asString()
    private val autoConverter = ksClass.getAnnotationsByType(AutoConvert::class).first()

    /**
     * All annotated symbols
     */
    private val allAnnotatedSymbols =
        resolver.getSymbolsWithAnnotation(AutoConvert::class.java.name)
            .filterIsInstance<KSClassDeclaration>()

    private val filename = className + autoConverter.filePostfix

    fun generate() {

    }
}