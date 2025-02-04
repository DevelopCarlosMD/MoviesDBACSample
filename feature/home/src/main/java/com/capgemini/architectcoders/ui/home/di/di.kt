package com.capgemini.architectcoders.ui.home.di

import com.capgemini.architectcoders.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/*@Module
@ComponentScan
class FeatureHomeModule*/

val featureHomeModule = module {
    viewModelOf(::HomeViewModel)
}