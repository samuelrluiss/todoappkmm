plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version embeddedKotlinVersion
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    /*
    So essentially we have 2 ways of doing this (with their own pros/cons):
        static framework (isStatic = true)

        doesn't require linking the Firebase SDKs when building KMM, this is because static libraries are just an archives of object files, so it will need to create those only from KMM code
        require linking the Firebase SDKs when building iOS app
        final app binary will be bigger than with dynamic framework
        compiler can strip unused parts of the static framework, so app binary can be smaller than app + dynamic framework
        dynamic framework (isStatic = false)

        require linking the Firebase SDKs when building KMM
        require linking the Firebase SDKs when building iOS app
        final app binary smaller than with the static framework
        compiler can't strip unused parts of the dynamic framework
        TBH - linking statically something that needs to be initialised at app start makes sense, because Apple doesn't recommend using too many dynamic frameworks in iOS apps & also there's noticeable latency when loading a bunch of dynamic frameworks at app start.
     */
    // export correct artifact to use all classes of library directly from Swift
    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.16.1")
            export("dev.icerock.moko:mvvm-livedata:0.16.1")
            export("dev.icerock.moko:mvvm-livedata-resources:0.16.1")
            export("dev.icerock.moko:mvvm-state:0.16.1")
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true //this gaves me tons of headaches
            //Shared Resources
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // This line
                implementation("dev.gitlive:firebase-firestore:1.10.4")
                //implementation("dev.icerock.moko:mvvm-core:0.16.1")
                //implementation("dev.icerock.moko:mvvm-flow:0.16.1")
                //implementation("dev.icerock.moko:mvvm-livedata:0.16.1")
                implementation("io.insert-koin:koin-core:3.2.0")
                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:mvvm-core:0.16.1")
                api("dev.icerock.moko:mvvm-livedata:0.16.1")
                api("dev.icerock.moko:mvvm-livedata-resources:0.16.1")
                api("dev.icerock.moko:mvvm-state:0.16.1")
            }
        }

        //Adding Android Specific dependency
        val androidMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-android:3.2.0")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.todoappkmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.example.todoappkmm"
    multiplatformResourcesClassName = "SharedResources" // optional, default MR
    iosBaseLocalizationRegion = "en" // optional, default "en"
    disableStaticFrameworkWarning = true
}