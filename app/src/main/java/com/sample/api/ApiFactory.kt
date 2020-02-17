package com.sample.api

import com.sample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiFactory : KoinComponent {
    private val interceptor: RetrofitInterceptor by inject()
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val baseUrl = "https://abc.sample.com/api/v1/"

    fun createApi(): SampleApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(provideHttpClient())
        .build()
        .create(SampleApi::class.java)

    private fun provideHttpClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
        .writeTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
        .build()

    companion object {
        private const val TIMEOUT_DEFAULT = 10L
    }
}