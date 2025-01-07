package com.capgemini.architectcoders.data

import com.capgemini.architectcoders.data.datasource.RegionDataSource

class RegionRepository(private val regionDataSource: RegionDataSource) {
    suspend fun findLastRegion() : String = regionDataSource.findLastRegion()
}

