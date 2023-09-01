pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
  plugins {
    id("com.squareup.sqldelight") version "1.5.3"
  }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "rntraccar"
include(":android")
include(":shared")
