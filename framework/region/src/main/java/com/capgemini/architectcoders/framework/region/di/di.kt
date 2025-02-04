package com.capgemini.architectcoders.framework.region.di

import android.content.Context
import android.location.Geocoder
import com.capgemini.architectcoders.domain.region.data.LocationDataSource
import com.capgemini.architectcoders.domain.region.data.RegionDataSource
import com.capgemini.architectcoders.framework.region.GeocoderRegionDataSource
import com.capgemini.architectcoders.framework.region.PlayServicesLocationDataSource
import com.google.android.gms.location.LocationServices
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

import org.koin.dsl.module

/*@Module
@ComponentScan
class FrameworkRegionModule*/

val frameworkRegionModule = module {
    factoryOf(::PlayServicesLocationDataSource) bind LocationDataSource::class
    factory { LocationServices.getFusedLocationProviderClient(get<Context>()) }
    factory { Geocoder(get()) }
    factoryOf(::GeocoderRegionDataSource) bind RegionDataSource::class
}