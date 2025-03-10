import com.android.build.api.dsl.ApplicationExtension
import com.capgemini.architectcoders.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    // Se llama cuando se aplica un plugin en gradle
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension>() {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
            }
        }
    }
}