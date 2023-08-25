plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
  id("maven-publish")
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
  }
    android {
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
                //put your multiplatform dependencies here
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
    namespace = "dev.victorlpgazolli.rntraccar"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
