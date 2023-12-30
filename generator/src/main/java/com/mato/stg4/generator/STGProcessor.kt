package com.mato.stg4.generator

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.mato.stg4.annotation.STGClass

class STGProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor, KSPLogger by environment.logger {
    companion object {
        private const val TAG = "STGProcessor"
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        warn("$TAG begin to process")
        resolver.getSymbolsWithAnnotation(STGClass::class.java.name)
            .map { it as KSClassDeclaration }
            .forEach {
                val properties = it.getAllProperties()
                for (prop in properties) {
                    warn(prop.info())
                }
            }
        return emptyList()
    }

    override fun onError() {
        super.onError()
        error("$TAG error")
    }

    override fun finish() {
        super.finish()
        warn("$TAG finish")
    }
}