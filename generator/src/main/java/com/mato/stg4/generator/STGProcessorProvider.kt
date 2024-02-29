package com.mato.stg4.generator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class STGProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        environment.logger.run {
            info("Kotlin version: ${environment.kotlinVersion}")
            info("Api Version: ${environment.apiVersion}")
            info("Compile Version: ${environment.compilerVersion}")
            info("Options: ${environment.options}")
        }
        return STGProcessor(environment)
    }
}