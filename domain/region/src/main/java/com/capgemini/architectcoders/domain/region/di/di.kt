package com.capgemini.architectcoders.domain.region.di


import com.capgemini.architectcoders.domain.region.data.RegionRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

/*@Module
@ComponentScan
class DomainRegionModule*/

val domainRegionModule = module {
    factoryOf(::RegionRepository)
}