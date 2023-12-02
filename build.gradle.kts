plugins {
    kotlin("multiplatform") version "1.9.21"
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