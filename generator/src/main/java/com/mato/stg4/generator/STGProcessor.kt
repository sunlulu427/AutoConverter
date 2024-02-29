package com.mato.stg4.generator

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.mato.stg4.annotation.AutoConvert
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.asClassName
import org.json.JSONObject

class STGProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor, KSPLogger by environment.logger {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        warn("Begin to process")
        val symbols = resolver.getSymbolsWithAnnotation(AutoConvert::class.java.name)
        symbols.filterIsInstance<KSClassDeclaration>()
            .forEach(this::generatorFuncForClass)
        return emptyList()
    }

    private fun generatorFuncForClass(ksClass: KSClassDeclaration) {
        warn("Try get ksClass.annotations.")
        val className = ksClass.simpleName.asString()
        val packageName = ksClass.packageName.asString()
        ksClass.annotations.filterIsInstance<AutoConvert>()
            .map {
                warn("find auto converter: $it")
            }
        val file = FileSpec.builder(packageName, "${className}Ext")
            .indent(" ".repeat(4))
        val receiverType = ClassName(packageName, className)
        val funBuilder = FunSpec.builder("toJSONObject")
            .receiver(receiverType)
            .returns(Result::class.asClassName().parameterizedBy(JSONObject::class.asClassName()))

        // begin control flow
        funBuilder.beginControlFlow("return kotlin.runCatching {")

        funBuilder.addStatement("val json = %T()", JSONObject::class.asClassName())
        ksClass.getAllProperties().forEach {
            val propName = it.simpleName.asString()
            funBuilder.addStatement("json.put(%S, this.%L)", propName, propName)
        }
        funBuilder.addStatement("json")

        // end control flow
        funBuilder.endControlFlow()
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
}