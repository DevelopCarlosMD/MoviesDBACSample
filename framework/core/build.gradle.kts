plugins {
    id("com.capgemini.architectcoders.android.library")
    id("com.capgemini.architectcoders.android.room")
    id("com.capgemini.architectcoders.jvm.retrofit")
    id("com.capgemini.architectcoders.di.library")
}

android {
    namespace = "com.capgemini.architectcoders.framework.core"
}

dependencies {
    implementation(project(":framework:movie"))
}