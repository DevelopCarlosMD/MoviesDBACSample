plugins {
    id("com.capgemini.architectcoders.jvm.library")
    id("com.capgemini.architectcoders.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.kotlinx.coroutines.core)
}