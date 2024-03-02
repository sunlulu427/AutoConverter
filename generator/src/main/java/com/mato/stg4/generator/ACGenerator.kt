package com.mato.stg4.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.mato.stg4.annotation.ACFunction
import com.mato.stg4.annotation.AutoConvert
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.asClassName
import org.json.JSONObject

/**
 * @date: 2024-02-29
 * @author: 孙路路 sunlulu.tomato
 */
@OptIn(KspExperimental::class)
class ACGenerator(
    private val env: SymbolProcessorEnvironment,
    private val ksClass: KSClassDeclaration,
) {
    private val className = ksClass.simpleName.asString()
    private val packageName = ksClass.packageName.asString()
    private val autoConverter = ksClass.getAnnotationsByType(AutoConvert::class).first()
    private val filename = className + autoConverter.filePostfix
    private val fileBuilder = FileSpec.builder(packageName, filename)

    init {
        val indent = env.options.getOrDefault("ac.indent", "4").toInt()
        // config it via gradle plugin?
        fileBuilder.indent(" ".repeat(indent))

        // add functions
        autoConverter.functions.forEach {
            fileBuilder.addFunction(it.toFunSpec())
        }
    }

    fun generate() {
        if (autoConverter.functions.isEmpty()) {
            return
        }
        val output = env.codeGenerator.createNewFile(
            dependencies = Dependencies(false),
            packageName = ksClass.packageName.asString(),
            fileName = filename
        )

        val code = fileBuilder.build().toString().toByteArray()
        output.write(code)
        output.close()

        // After the file is generated, make it read-only
        env.codeGenerator.generatedFile.find {
            it.nameWithoutExtension == filename
        }?.setReadOnly()?.let {
            env.logger.info("Generated file: $filename is set read-only.")
        }
    }

    private fun ACFunction.toFunSpec(): FunSpec = when (this) {
        ACFunction.ToJSONObject -> {
            // generate fun toJSONObject
            val name = "toJSONObject"
            val receiverType = ClassName(packageName, className)
            val builder = FunSpec.builder(name)
                .receiver(receiverType)
                .returns(
                    Result::class.asClassName().parameterizedBy(JSONObject::class.asClassName())
                )

            // begin control flow of runCatching
            builder.beginControlFlow("return kotlin.runCatching {")
            // begin control flow of also
            builder.beginControlFlow("%T().also {", JSONObject::class.asClassName())
            ksClass.getAllProperties().forEach {
                val propName = it.simpleName.asString()
                val value = if (it.isAnnotatedClass()) {
                    // enable nested classes
                    val nestedPackageName = it.type.resolve().declaration.packageName.asString()
                    fileBuilder.addImport(nestedPackageName, name)
                    "this.%L.$name().getOrThrow()"
                } else {
                    "this.%L"
                }
                builder.addStatement(
                    "it.put(%S, $value)",
                    autoConverter.namingStrategy.resolve(propName),
                    propName
                )
            }
            builder.endControlFlow() // end control flow of also
            builder.endControlFlow() // end control flow of runCatching
            builder.build()
        }

        ACFunction.FromJSONObject -> {
            TODO("Not yet implemented")
        }
    }
}