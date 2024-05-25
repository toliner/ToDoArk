plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

group = "dev.toliner"

repositories {
    mavenCentral()
}

dependencies {
}

kotlin {
    jvmToolchain(21)

    jvm()
}