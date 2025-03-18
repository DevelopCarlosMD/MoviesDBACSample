plugins {
    id("com.capgemini.architectcoders.android.library")
    id("com.capgemini.architectcoders.di.library")
}

android {
    namespace = "com.capgemini.architectcoders.framework.region"
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.play.services.location)
}