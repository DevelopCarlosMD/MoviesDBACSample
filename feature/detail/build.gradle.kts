plugins {
    id("com.capgemini.architectcoders.android.feature")
}

android {
    namespace = "com.capgemini.architectcoders.ui.detail"
}

dependencies {
    implementation(project(":domain:movie"))
}