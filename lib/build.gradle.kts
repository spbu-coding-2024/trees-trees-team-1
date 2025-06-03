repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "2.1.10"
    `java-library`
    jacoco
    id("org.jetbrains.dokka") version "2.0.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

dependencies {
    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine:1.8.2")
    testImplementation("org.junit.platform:junit-platform-suite:1.13.0-M2")
    testImplementation("net.jqwik:jqwik-kotlin:1.9.1")
    testImplementation("org.assertj:assertj-core:3.23.1")
    compileOnly("org.jetbrains:annotations:23.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.register<Test>("BRTree") {
    description="Runs tests only on Black-Red tree."
    group="Verification"
    useJUnitPlatform {
        includeTags("BRTree")
    }
}

tasks.register<Test>("AVLTree") {
    description="Runs tests only on AVL tree."
    group="Verification"
    useJUnitPlatform {
        includeTags("AVLTree")
    }
}

tasks.register<Test>("BSTree") {
    description="Runs tests only on BS tree."
    group="Verification"
    useJUnitPlatform {
        includeTags("BSTree")
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        csv.required = false
        xml.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}