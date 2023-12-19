plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "me.mark"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
}

application {
    mainClass.set("MainKt")
}