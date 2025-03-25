import java.util.Properties

plugins {
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    id("com.capgemini.architectcoders.android.application")
    id("com.capgemini.architectcoders.android.application.compose")
    id("com.capgemini.architectcoders.di.library.compose")
}

android {
    namespace = "com.capgemini.architectcoders"

    defaultConfig {
        applicationId = "com.capgemini.architectcoders"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.capgemini.architectcoders.di.HiltTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").readText().byteInputStream())

        val tmdbApiKey = properties.getProperty("TMDB_API_KEY", "")
        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":domain:movie"))
    implementation(project(":domain:region"))
    implementation(project(":feature:home"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:common"))
    implementation(project(":framework:core"))
    implementation(project(":framework:movie"))
    implementation(project(":framework:region"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.location)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.androidx.room.ktx)
    kspAndroidTest(libs.androidx.room.compiler)
    androidTestImplementation(libs.okhttp.mockwebserver)
}