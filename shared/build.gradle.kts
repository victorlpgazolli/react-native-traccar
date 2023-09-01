plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("maven-publish")
  id("app.cash.sqldelight") version "2.0.0"
    kotlin( "plugin.serialization")
}


kotlin {
  publishing {
    publications {
      create<MavenPublication>("maven") {
        from(components["kotlin"])
        groupId = "dev.victorlpgazolli.rntraccar"
        artifactId = "android"
        version = "0.0.1"
      }
    }
    repositories {
      mavenCentral()
      google()
      mavenLocal()
    }
  }
    android {
      publishLibraryVariants("release", "debug")

    }

    ios()
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
              implementation("app.cash.sqldelight:runtime:2.0.0")
              implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2")
            }

        }
        val androidMain by getting {
            dependencies {
              implementation("app.cash.sqldelight:android-driver:2.0.0")
            }
        }
        val iosMain by getting {
          dependencies {
            implementation("app.cash.sqldelight:native-driver:2.0.0")
          }
        }
    }
}

android {
    namespace = "dev.victorlpgazolli.rntraccar"
    compileSdk = 33
    defaultConfig {
        minSdk = 24

    }
    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
  databases {
    create("TraccarDatabase") {
      packageName.set("dev.victorlpgazolli.rntraccar.database")
    }
  }
}
