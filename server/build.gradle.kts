plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kapt)
    alias(libs.plugins.allopen)
    alias(libs.plugins.ktlint)
    id("io.micronaut.application") version "4.3.8"
    id("io.micronaut.test-resources") version "4.3.8"
    id("io.micronaut.aot") version "4.3.8"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.google.cloud.tools.jib") version "2.8.0"
}

version = "0.1"
group = "dev.toliner"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.openapi:micronaut-openapi")
    kapt("io.micronaut.openapi:micronaut-openapi-adoc")
    kapt("io.micronaut.security:micronaut-security-annotations")
    kapt("io.micronaut.serde:micronaut-serde-processor")
    kapt("io.micronaut.data:micronaut-data-processor")
    implementation("io.micronaut:micronaut-aop")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut:micronaut-retry")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.security:micronaut-security-oauth2")
    implementation("io.micronaut.security:micronaut-security-session")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.data:micronaut-data-jdbc")
    implementation("io.micrometer:context-propagation")
    implementation("io.micronaut.reactor:micronaut-reactor")
    implementation("org.apache.logging.log4j:log4j-api")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.23.1"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:${libs.versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${libs.versions.kotlin}")
    implementation(libs.kotlinx.coroutine)
    compileOnly("io.micronaut.openapi:micronaut-openapi-annotations")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.apache.logging.log4j:log4j-core")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl")
    runtimeOnly("org.postgresql:postgresql")
    developmentOnly("io.micronaut.controlpanel:micronaut-control-panel-management")
    developmentOnly("io.micronaut.controlpanel:micronaut-control-panel-ui")
    aotPlugins(platform("io.micronaut.platform:micronaut-platform:${libs.versions.micronaut}"))
    aotPlugins("io.micronaut.security:micronaut-security-aot")
}

application {
    mainClass = "dev.toliner.todoark.server.ApplicationKt"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks {
    jib {
        to {
            image = "gcr.io/myapp/jib-image"
        }
    }
}
graalvmNative.toolchainDetection = false
micronaut {
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("dev.toliner.*")
    }
    testResources {
        additionalModules.add("jdbc-postgresql")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
        configurationProperties.put("micronaut.security.jwks.enabled", "false")
        configurationProperties.put("micronaut.security.openid-configuration.enabled", "false")
    }
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}

allOpen {
    annotations(
        "io.micronaut.aop.Around",
        "io.micronaut.aop.Introduction",
    )
}
