plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.1.0").apply(false)
    id("com.android.library").version("8.1.0").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("com.google.gms.google-services") version "4.4.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

//Sharing Resources
buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath ("dev.icerock.moko:resources-generator:0.23.0")
    }
}

gradle.taskGraph.whenReady {
    if (project.hasProperty("noAppApple")) {
        allTasks.asSequence()
            .filter {
                it.path.startsWith(":app:ios-combine") ||
                        it.path.startsWith(":app:macos") ||
                        it.path.startsWith(":app:web")
            }
            .forEach {
                it.enabled = false
            }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}