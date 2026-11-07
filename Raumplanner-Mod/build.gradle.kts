plugins {
    java
}

group = "cho.info"
version = rootProject.version

val agentJar = layout.projectDirectory.file("libs/universal-mixin.jar")
val roomPlannerJarOverride = providers.gradleProperty("roomPlannerJar")
val roomPlannerJar = rootProject.tasks.named<Jar>("shadowJar")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 17
    options.encoding = "UTF-8"
}

dependencies {
    compileOnly(files(agentJar))
}

val modJar = tasks.named<Jar>("jar")

tasks.register<Exec>("run") {
    group = "application"
    description = "Starts the room planner with the external UniversalMixin mod."
    dependsOn(modJar)
    if (!roomPlannerJarOverride.isPresent) {
        dependsOn(roomPlannerJar)
    }

    doFirst {
        val targetJar = if (roomPlannerJarOverride.isPresent) {
            rootProject.file(roomPlannerJarOverride.get())
        } else {
            roomPlannerJar.get().archiveFile.get().asFile
        }
        check(agentJar.asFile.isFile) {
            "Missing libs/universal-mixin.jar. Copy the bundled UniversalMixin agent JAR here."
        }
        check(targetJar.isFile) {
            "Room planner JAR not found: $targetJar. " +
                "Specify -ProomPlannerJar=/absolute/path/to/roomplanner.jar."
        }
        val javaExecutable = javaToolchains.launcherFor(java.toolchain)
            .get().executablePath.asFile.absolutePath
        commandLine(
            javaExecutable,
            "-javaagent:${agentJar.asFile.absolutePath}=${modJar.get().archiveFile.get().asFile.absolutePath}",
            "-jar", targetJar.absolutePath
        )
    }
}
