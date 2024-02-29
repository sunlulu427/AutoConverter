package com.mato.stg4.generator

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.mato.stg4.annotation.AutoConvert

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/31
 */

fun KSPropertyDeclaration.info(): String {
    val location = this.location as FileLocation
    val resolvedType = this.type.resolve()
    return """
    name: ${this.simpleName.asString()}
    isMutable: $isMutable
    hasBackingField: $hasBackingField
    location: ${location.filePath} at line ${location.lineNumber}
    type: $resolvedType
    declaration: ${resolvedType.declaration}
    qualifiedName: ${resolvedType.declaration.qualifiedName?.asString()}
    isMutabilityFlexible: ${resolvedType.isMutabilityFlexible()}
    isMarkedNullable: ${resolvedType.isMarkedNullable}
    isFunctionType: ${resolvedType.isFunctionType}
    isSuspendFunctionType: ${resolvedType.isSuspendFunctionType}
    isCovarianceFlexible: ${resolvedType.isCovarianceFlexible()}
    arguments: ${resolvedType.arguments}
    """
}


fun KSAnnotation.asAutoConvert(): AutoConvert {
    TODO()
}

inline fun <reified T : Any> KSAnnotation.match(): Boolean {
    return T::class.qualifiedName == annotationType.resolve().declaration.qualifiedName?.asString()
}

inline fun <reified T : Any> KSClassDeclaration.firstAnnotationWith(): KSAnnotation {
    return annotations.first { it.match<T>() }
}