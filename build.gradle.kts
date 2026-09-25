import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform

plugins {
    id("java")
    id("application")
    id("com.gradleup.shadow") version "9.4.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    implementation("uk.co.electronstudio.jaylib:jaylib:6.0.+")

    implementation("io.github.elebras1:flecs-java:0.15.0")
    annotationProcessor("io.github.elebras1:flecs-java:0.15.0")

    compileOnly("org.projectlombok:lombok:1.18.48")
    annotationProcessor("org.projectlombok:lombok:1.18.48")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("cho.info.Main")
    if(DefaultNativePlatform.getCurrentOperatingSystem().isMacOsX()) {
        applicationDefaultJvmArgs += "-XstartOnFirstThread"
    }
}

tasks.test {
    useJUnitPlatform()
}

distributions {
    main {
        contents {
            into("resources") {
                from("resources")
            }
        }
    }
}