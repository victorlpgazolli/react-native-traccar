plugins {
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("maven-publish")
//    id("com.squareup.sqldelight")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

group = "dev.victorlpgazolli.rntraccar"
version = "0.0.1"

buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
//    classpath("com.squareup.sqldelight:gradle-plugin:1.5.4")
    classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.21")
  }
}
