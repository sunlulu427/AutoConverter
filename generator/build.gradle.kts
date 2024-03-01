plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":annotation"))
    // KSP
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.13") {
        exclude(module = "kotlin-reflect")
    }
    // kotlin poet
    implementation("com.squareup:kotlinpoet:1.12.0")
    implementation("com.squareup:kotlinpoet-ksp:1.12.0")
    implementation("org.json:json:20230227")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}
