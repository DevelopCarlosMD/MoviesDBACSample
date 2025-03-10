plugins {
    id("com.capgemini.architectcoders.android.library.compose")
}

android {
    namespace = "com.capgemini.architectcoders.ui.common"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}