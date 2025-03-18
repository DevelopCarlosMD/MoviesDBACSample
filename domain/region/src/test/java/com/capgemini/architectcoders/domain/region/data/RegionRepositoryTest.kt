package com.capgemini.architectcoders.domain.region.data

import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class RegionRepositoryTest {

    @Test
    fun `findLastRegion calls RegionDataSource`(): Unit = runBlocking {
        val repository = RegionRepository(mock {
            onBlocking { findLastRegion() } doReturn "ES"
        })

        val region = repository.findLastRegion()

        assertEquals("ES", region)
    }
}
