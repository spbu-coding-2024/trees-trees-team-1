plugins {
    kotlin("jvm") version "2.1.10"
<<<<<<< HEAD
=======
    jacoco
    id("java")
>>>>>>> ed2657a (final tests)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

<<<<<<< HEAD
<<<<<<< HEAD
tasks.test {
    useJUnitPlatform()
}
=======
>>>>>>> f3b501a (trash deletion)
kotlin {
<<<<<<< HEAD
    jvmToolchain(22)
=======
    jvmToolchain(23)
}

=======


task<Javadoc>("javado") {
    description = "Generates Javadoc for"
    source("src/main/kotlin")
    classpath=files("src/main/kotlin")
    println( destinationDir)
}
kotlin {
    jvmToolchain(23)
}


>>>>>>> ed2657a (final tests)
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

<<<<<<< HEAD
=======

<<<<<<< HEAD
>>>>>>> f3b501a (trash deletion)
=======

>>>>>>> ed2657a (final tests)
tasks.named<Test>("test") {
    dependsOn("testRBtree")
    finalizedBy(tasks.findByPath("jacocoTestReport"))
}

<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> f3b501a (trash deletion)
=======
tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("dokka"))
}

>>>>>>> ed2657a (final tests)

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        csv.required = false
        xml.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 3b04d04 (fix)
}
=======
}
>>>>>>> f3b501a (trash deletion)
=======
}
>>>>>>> ed2657a (final tests)
