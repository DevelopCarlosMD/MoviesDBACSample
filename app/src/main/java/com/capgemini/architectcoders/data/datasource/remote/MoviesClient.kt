package com.capgemini.architectcoders.data.datasource.remote

import com.capgemini.architectcoders.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

object MoviesClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { apiKeyAsQuery(it) }
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    // Notes:
    // 1. If you have java objects use Moshi if project is 100 kotlin uses kotlinx.serialization (easy integration with multiplatform)
    val instance = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType())) // convert json to kotlin object
        .build()
        .create<MoviesService>()
}

// TODO: retrofit uses to capture http request and if we want we can add additional elements like headers
//.addInterceptor(::apiKeyAsQuery) similar call for interceptors
private fun apiKeyAsQuery(chain: Interceptor.Chain): Response {
    return chain.proceed(
        chain.request()
            .newBuilder()
            .url(
                chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build()
            ).build()
    )
}