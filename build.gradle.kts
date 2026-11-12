plugins {
    id("java")
    id("application")
    id("com.gradleup.shadow") version "9.4.1"
}

group = "cho.info"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

application {
    mainClass = "cho.info.Main"
}
