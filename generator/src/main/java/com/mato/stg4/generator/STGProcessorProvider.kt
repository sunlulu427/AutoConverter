package com.mato.stg4.generator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class STGProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        environment.logger.run {
            warn("Kotlin version: ${environment.kotlinVersion}")
            warn("Api Version: ${environment.apiVersion}")
            warn("Compile Version: ${environment.compilerVersion}")
            warn("Options: ${environment.options}")
        }
        return STGProcessor(environment)
    }
}