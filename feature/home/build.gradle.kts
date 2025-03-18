plugins {
    id("com.capgemini.architectcoders.android.feature")
    id("com.capgemini.architectcoders.di.library.compose")
}

android {
    namespace = "com.capgemini.architectcoders.ui.home"
}

dependencies {
    implementation(project(":domain:movie"))
}