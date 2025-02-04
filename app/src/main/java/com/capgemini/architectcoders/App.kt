package com.capgemini.architectcoders

import android.app.Application
import com.capgemini.architectcoders.domain.movie.di.domainMovieModule
import com.capgemini.architectcoders.domain.region.di.domainRegionModule
import com.capgemini.architectcoders.framework.core.di.frameworkCoreModule
import com.capgemini.architectcoders.framework.movie.di.frameworkMovieModule
import com.capgemini.architectcoders.framework.region.di.frameworkRegionModule
import com.capgemini.architectcoders.ui.detail.di.featureDetailModule
import com.capgemini.architectcoders.ui.home.di.featureHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                appModule,
                featureHomeModule,
                featureDetailModule,
                domainMovieModule,
                domainRegionModule,
                frameworkCoreModule,
                frameworkMovieModule,
                frameworkRegionModule
            )
        }
    }
}

val appModule = module {
    single(named("apiKey")) { BuildConfig.TMDB_API_KEY }
}