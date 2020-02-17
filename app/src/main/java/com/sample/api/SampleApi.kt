package com.sample.api

import com.sample.api.model.Patient
import retrofit2.Call
import retrofit2.http.POST

interface SampleApi {
    @POST("sample_endpoint")
    fun loginDoctor(): Call<List<Patient>>
}