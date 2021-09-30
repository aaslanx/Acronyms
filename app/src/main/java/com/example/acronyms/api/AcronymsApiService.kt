package com.example.acronyms.api

import com.example.acronyms.model.AcronymResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AcronymsApiService() {

    private val acronymsApi: AcronymsApi by lazy {

        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor).readTimeout(
                60,
                TimeUnit.SECONDS
            )
        }

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AcronymsApi::class.java)

    }

    suspend fun getAcronyms(searchFor: String): List<AcronymResponse> {
        return acronymsApi.getAcronyms(searchFor)
    }

    companion object {
        const val BASE_URL = "http://www.nactem.ac.uk/"
    }
}