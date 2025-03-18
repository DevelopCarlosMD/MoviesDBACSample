package com.capgemini.architectcoders.framework.region

import android.app.Application
import android.location.Geocoder
import com.capgemini.architectcoders.domain.region.data.LocationDataSource
import com.capgemini.architectcoders.domain.region.data.RegionDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkRegionBindsModule {

    @Binds
    abstract fun bindLocationDataSource(locationDataSource: PlayServicesLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindsRegionDataSource(regionDataSource: GeocoderRegionDataSource): RegionDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object FrameworkRegionModule {

    @Provides
    fun provideFusedLocationClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    fun provideGeocoder(app: Application) = Geocoder(app)
}