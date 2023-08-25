plugins {
//    id("com.android.application")
    id("com.android.library")
    kotlin("android")
  id("maven-publish")
}

android {
    namespace = "dev.victorlpgazolli.rntraccar.android"
    compileSdk = 33
    defaultConfig {
//        applicationId = "dev.victorlpgazolli.rntraccar.android"
        minSdk = 24
        targetSdk = 33
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("com.facebook.react:react-android:+")
    implementation("com.facebook.react:hermes-android:+")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("com.github.judemanutd:autostarter:1.1.0")
}
