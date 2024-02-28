package com.mato.stg4.generator

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.mato.stg4.annotation.FormatJson
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class STGProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor, KSPLogger by environment.logger {
    companion object {
        private const val TAG = "STGProcessor"
        private val kotlinResult = ClassName("kotlin", "Result")
        private val jsonObjectClassName = ClassName("org.json", "JSONObject")

    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        warn("$TAG begin to process")
        val symbols = resolver.getSymbolsWithAnnotation(FormatJson::class.java.name)
        symbols.filterIsInstance<KSClassDeclaration>()
            .forEach(this::generatorFuncForClass)
        return emptyList()
    }

    private fun generatorFuncForClass(ksClass: KSClassDeclaration) {
        warn("Try get ksClass.annotations.")
        ksClass.annotations.filter { it.match<FormatJson>() }
            .forEach {
                warn("matched: $it")
                val formatJson = it.build<FormatJson>()
                warn(formatJson.toString())
            }
        val className = ksClass.simpleName.asString()
        val file = FileSpec.builder(ksClass.packageName.asString(), "${className}Ext")
        val receiverType = ClassName(ksClass.packageName.asString(), ksClass.simpleName.asString())
        val funBuilder = FunSpec.builder("toJSONObject")
            .receiver(receiverType)
            .returns(kotlinResult.parameterizedBy(jsonObjectClassName))
        funBuilder.addStatement("val json = %T()", jsonObjectClassName)
        ksClass.getAllProperties().forEach {
            val propName = it.simpleName.asString()
            funBuilder.addStatement("json.put(%S, this.%L)", propName, propName)
        }
        funBuilder.addStatement("return Result.success(json)")
        file.addFunction(funBuilder.build())
        val output = environment.codeGenerator.createNewFile(
            dependencies = Dependencies(false),
            packageName = ksClass.packageName.asString(),
            fileName = "${className}Ext"
        )

        val code = file.build().toString().toByteArray()
        output.write(code)
        output.close()
    }

    private fun KSPropertyDeclaration.toJsonElement(): Any {
        return 0
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