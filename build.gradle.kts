import org.gradle.api.problems.internal.PropertyTraceDataSpec
import org.gradle.internal.configuration.problems.taskPathFrom

plugins {
    kotlin("jvm") version "2.1.10"
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
    jacoco
    id("java")
>>>>>>> ed2657a (final tests)
=======
    jacoco
>>>>>>> 91d29c7 (remake project test files structure)
=======
    jacoco
>>>>>>> 91d29c7 (remake project test files structure)
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
tasks.test {
    useJUnitPlatform()
}
=======
>>>>>>> f3b501a (trash deletion)
=======
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
kotlin {
<<<<<<< HEAD
    jvmToolchain(22)
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
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
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
}

tasks.register<Test>("RBInsert") {
    dependsOn("RBBasic")
<<<<<<< HEAD
<<<<<<< HEAD
    useJUnitPlatform { filter{ includeTags("insert & BRTree")} }
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
    useJUnitPlatform {
        filter {
            includeTags("insert & BRTree")
        }
    }
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
}

tasks.register<Test>("RBDelete") {
    dependsOn("RBInsert")
<<<<<<< HEAD
<<<<<<< HEAD
    useJUnitPlatform { filter { includeTags("delete & BRTree") } }
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
    useJUnitPlatform {
        filter {
            includeTags("delete & BRTree")
        }
    }
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
}

tasks.register<Test>("testRBtree") {
    dependsOn("RBDelete")
}

<<<<<<< HEAD
<<<<<<< HEAD
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

=======
=======
>>>>>>> 91d29c7 (remake project test files structure)

tasks.named<Test>("test") {
    useJUnitPlatform()
    tasks.findByPath("testRBtree")
    testLogging.showStandardStreams=true
    finalizedBy(tasks.findByPath("jacocoTestReport"))

}

<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        csv.required = false
        xml.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)


}







<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
