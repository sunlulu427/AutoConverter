plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":annotation"))
    // KSP
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.13")
}