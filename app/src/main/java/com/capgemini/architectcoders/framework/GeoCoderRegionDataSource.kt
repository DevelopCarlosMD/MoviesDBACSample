package com.capgemini.architectcoders.framework

import android.location.Geocoder
import com.capgemini.architectcoders.data.datasource.DEFAULT_REGION
import com.capgemini.architectcoders.data.datasource.LocationDataSource
import com.capgemini.architectcoders.data.datasource.RegionDataSource
import com.capgemini.architectcoders.ui.common.getFromLocationCompat
import com.capgemini.architectcoders.domain.Location

class GeoCoderRegionDataSource(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : RegionDataSource {
    override suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    private suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}