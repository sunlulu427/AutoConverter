package com.mato.stg4.generator

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.mato.stg4.annotation.AutoConvert

class AutoConvertProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor, KSPLogger by environment.logger {

    private var invoked = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) {
            return emptyList()
        }
        val symbols = resolver.getSymbolsWithAnnotation(AutoConvert::class.java.name)
        symbols.filterIsInstance<KSClassDeclaration>()
            .forEach {
                ACGenerator(environment, it).generate()
            }
        invoked = true
        return emptyList()
    }
}