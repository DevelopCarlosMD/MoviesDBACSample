package com.capgemini.architectcoders.domain.region.data

import com.capgemini.architectcoders.domain.region.entities.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}