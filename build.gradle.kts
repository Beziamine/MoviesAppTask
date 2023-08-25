buildscript {

    val kotlin_version = "1.5.31"
    val build_gradle_version = "7.3.1"
    val hilt_version = "2.43.2"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$build_gradle_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
}

tasks.register<Delete>("clean"){
    delete(rootProject.buildDir)
}