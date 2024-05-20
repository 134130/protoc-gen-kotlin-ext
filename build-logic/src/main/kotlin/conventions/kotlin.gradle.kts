package conventions

import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    kotlin("jvm")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
        vendor = JvmVendorSpec.ADOPTIUM
    }
}

kotlin {
    compilerOptions {
        javaParameters = true
        allWarningsAsErrors = true
        freeCompilerArgs = listOf(
            "-Xjsr305=strict",
        )
    }
}

val libs = the<LibrariesForLibs>()

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.assertk)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        // Make sure output from standard out or error is shown in Gradle output.
        showExceptions = true
        showCauses = true
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
