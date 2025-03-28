plugins {
    kotlin("jvm") version "2.1.10"
    `java-library`
    jacoco
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    // https://mvnrepository.com/artifact/net.jqwik/jqwik-kotlin
    testImplementation("net.jqwik:jqwik-kotlin:1.9.1")
    // Add any other test library you need...
    testImplementation("org.assertj:assertj-core:3.23.1")

    // Optional but recommended to get annotation related API warnings
    compileOnly("org.jetbrains:annotations:23.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(kotlin("test"))
}
tasks.test {


}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
    finalizedBy(tasks.named("jacocoTestReport"))
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        csv.required = false
        xml.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}