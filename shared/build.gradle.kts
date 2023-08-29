plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
  id("maven-publish")
  id("com.squareup.sqldelight")
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
      mavenLocal()
    }
  }
    android {
      publishLibraryVariants("release", "debug")
      compilations.all {
          kotlinOptions {
              jvmTarget = "1.8"
          }
      }
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
              implementation("com.squareup.sqldelight:runtime:1.5.3")
              implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2")
            }

        }
        val androidMain by getting {
            dependencies {
              implementation("com.squareup.sqldelight:android-driver:1.5.3")
            }
        }
        val iosMain by getting {
          dependencies {
            implementation("com.squareup.sqldelight:native-driver:1.5.3")
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
}

sqldelight {
  database("CommonDatabase"){
    packageName = "dev.victorlpgazolli.rntraccar.database"
  }
}
