plugins {
    kotlin("multiplatform") version "1.9.21"
    application
}

group = "me.mark"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    mingwX64("win") {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    jvm {
        withJava()
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
                implementation(kotlin("test"))
            }
        }
    }
}

application {
    mainClass.set("MainKt")
}