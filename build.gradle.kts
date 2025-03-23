plugins {
    kotlin("jvm") version "2.1.10"
    jacoco
    id("java")
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
    jvmToolchain(23)
}

tasks.register<Test>("RBBasic") {
    useJUnitPlatform { filter { includeTags("basic & BRTree") } }
}

tasks.register<Test>("RBInsert") {
    dependsOn("RBBasic")
    useJUnitPlatform { filter{ includeTags("insert & BRTree")} }
}

tasks.register<Test>("RBDelete") {
    dependsOn("RBInsert")
    useJUnitPlatform { filter { includeTags("delete & BRTree") } }
}

tasks.register<Test>("testRBtree") {
    dependsOn("RBDelete")
}


tasks.named<Test>("test") {
    dependsOn("testRBtree")
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
