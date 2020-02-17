package com.sample.api.model

import com.squareup.moshi.Json

data class ErrorResponse(
    @field:Json(name = "error")
    val error: String?
)