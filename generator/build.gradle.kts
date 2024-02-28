plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":annotation"))
    // KSP
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.13")
    // kotlin poet
    implementation("com.squareup:kotlinpoet:1.12.0")
    // kotlin reflect
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    compileOnly("org.json:json:20230227")
}