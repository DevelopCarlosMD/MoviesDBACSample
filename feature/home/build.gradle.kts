plugins {
    id("com.capgemini.architectcoders.android.feature")
}

android {
    namespace = "com.capgemini.architectcoders.ui.home"
}

dependencies {
    implementation(project(":domain:movie"))
}