package com.mato.stg4.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.mato.stg4.annotation.ACFunction
import com.mato.stg4.annotation.AutoConvert

/**
 * @date: 2024-03-02
 * @author: 孙路路 sunlulu.tomato
 */
@OptIn(KspExperimental::class)
fun KSPropertyDeclaration.isAnnotatedClass(): Boolean {
    val propType = this.type.resolve().declaration
    return propType is KSClassDeclaration && propType.isAnnotationPresent(AutoConvert::class)
}

@OptIn(KspExperimental::class)
val KSValueParameter.isAnnotatedParameter: Boolean
    get() = type.resolve().declaration.isAnnotationPresent(AutoConvert::class)

val KSValueParameter.isGenericType: Boolean
    get() = type.resolve().arguments.isNotEmpty()


fun String.withIndent(indent: Int) = " ".repeat(indent) + this

fun String.withComma(condition: Boolean = true): String = if (condition) "$this," else this