package com.capgemini.architectcoders.data.datasource

import com.capgemini.architectcoders.domain.Location


interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}