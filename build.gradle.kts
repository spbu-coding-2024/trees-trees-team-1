import org.gradle.api.problems.internal.PropertyTraceDataSpec
import org.gradle.internal.configuration.problems.taskPathFrom

plugins {
    kotlin("jvm") version "2.1.10"
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(22)
}

sourceSets.main {
    kotlin.srcDirs("src/main/kotlin")
}


tasks.register<Test>("RBBasic") {
    useJUnitPlatform {
        filter {
            includeTags("basic & BRTree")
        }
    }
}

tasks.register<Test>("RBInsert") {
    dependsOn("RBBasic")
    useJUnitPlatform {
        filter {
            includeTags("insert & BRTree")
        }
    }
}

tasks.register<Test>("RBDelete") {
    dependsOn("RBInsert")
    useJUnitPlatform {
        filter {
            includeTags("delete & BRTree")
        }
    }
}

tasks.register<Test>("testRBtree") {
    dependsOn("RBDelete")
}


tasks.named<Test>("test") {
    useJUnitPlatform()
    tasks.findByPath("testRBtree")
    testLogging.showStandardStreams=true
    finalizedBy(tasks.findByPath("jacocoTestReport"))

}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        csv.required = false
        xml.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }


}







