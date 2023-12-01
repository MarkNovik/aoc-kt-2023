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
    jvm {
        withJava()
        jvmToolchain(8)
    }
    mingwX64 {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
                implementation(kotlin("test"))
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}