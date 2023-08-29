plugins {
    //trick: for the same plugin versions in all sub-modules
//    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
  id("maven-publish")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
group = "dev.victorlpgazolli.rntraccar"
version = "0.0.1"

buildscript {
  dependencies {
    classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
    classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.21")
  }
}
