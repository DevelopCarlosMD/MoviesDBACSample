plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.capgemini.architectcoders.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "com.capgemini.architectcoders.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.capgemini.architectcoders.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "com.capgemini.architectcoders.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeature") {   {}
            id = "com.capgemini.architectcoders.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("jvmLibrary") {
            id = "com.capgemini.architectcoders.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("androidRoom") {
            id = "com.capgemini.architectcoders.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("jvmRetrofit") {
            id = "com.capgemini.architectcoders.jvm.retrofit"
            implementationClass = "JvmRetrofitConventionPlugin"
        }
    }
}