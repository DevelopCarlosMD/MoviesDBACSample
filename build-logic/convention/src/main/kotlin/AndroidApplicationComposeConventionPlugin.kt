import com.android.build.api.dsl.ApplicationExtension
import com.capgemini.architectcoders.addAndroidTestDependencies
import com.capgemini.architectcoders.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")

                val extension = extensions.getByType<ApplicationExtension>()
                configureAndroidCompose(extension)

                addAndroidTestDependencies()
            }
        }
    }
}