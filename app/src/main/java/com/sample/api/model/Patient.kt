package com.sample.api.model

import com.squareup.moshi.Json

data class Patient(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String
)