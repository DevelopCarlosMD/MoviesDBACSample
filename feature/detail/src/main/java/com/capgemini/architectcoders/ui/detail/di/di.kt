package com.capgemini.architectcoders.ui.detail.di

import com.capgemini.architectcoders.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


/*@Module
@ComponentScan
class FeatureDetailModule*/

val featureDetailModule = module {
    viewModelOf(::DetailViewModel)
}