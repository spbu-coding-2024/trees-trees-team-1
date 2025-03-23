plugins {
    kotlin("jvm") version "2.1.10"
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

>>>>>>> f3b501a (trash deletion)
tasks.named<Test>("test") {
    dependsOn("testRBtree")
    finalizedBy(tasks.findByPath("jacocoTestReport"))
}

<<<<<<< HEAD

=======
>>>>>>> f3b501a (trash deletion)

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        csv.required = false
        xml.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
<<<<<<< HEAD
>>>>>>> 3b04d04 (fix)
}
=======
}
>>>>>>> f3b501a (trash deletion)
