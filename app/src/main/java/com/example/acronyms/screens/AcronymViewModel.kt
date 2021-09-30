package com.example.acronyms.screens

import androidx.lifecycle.ViewModel
import com.example.acronyms.api.AcronymsApiService
import com.example.acronyms.model.AcronymResponse

class AcronymViewModel : ViewModel() {

    suspend fun getAcronyms(shortForm: String): List<AcronymResponse> {
        return AcronymsApiService().getAcronyms(shortForm)
    }
}