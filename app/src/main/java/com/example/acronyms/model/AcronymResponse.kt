package com.example.acronyms.model

data class AcronymResponse(
    val sf: String,
    val lfs: List<Lf>
)

data class Lf(
    val lf: String,
    val freq: Int,
    val since: Int,
    val vars: List<Lf>?
)
