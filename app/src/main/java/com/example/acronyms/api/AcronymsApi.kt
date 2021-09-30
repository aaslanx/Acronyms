package com.example.acronyms.api

import com.example.acronyms.model.AcronymResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymsApi {

    @GET("/software/acromine/dictionary.py")
    suspend fun getAcronyms(
        @Query("sf") acronym: String
    ): List<AcronymResponse>
}