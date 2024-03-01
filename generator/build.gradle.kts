plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":annotation"))
    // KSP
    implementation("com.google.devtools.ksp:symbol-processing-api:1.7.21-1.0.8") {
        exclude(module = "kotlin-reflect")
    }
    // kotlin poet
    implementation("com.squareup:kotlinpoet:1.12.0")
    implementation("com.squareup:kotlinpoet-ksp:1.12.0")
    implementation("org.json:json:20230227")
}