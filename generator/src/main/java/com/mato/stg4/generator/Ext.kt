package com.mato.stg4.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.mato.stg4.annotation.AutoConvert
import com.mato.stg4.annotation.ACFunction

/**
 * @date: 2024-03-02
 * @author: 孙路路 sunlulu.tomato
 */
@OptIn(KspExperimental::class)
fun KSPropertyDeclaration.isAnnotatedClass(): Boolean {
    val propType = this.type.resolve().declaration
    return propType is KSClassDeclaration && propType.isAnnotationPresent(AutoConvert::class)
}


val ACFunction.funName: String
    get() = this.name.replaceFirstChar { it.lowercase() }