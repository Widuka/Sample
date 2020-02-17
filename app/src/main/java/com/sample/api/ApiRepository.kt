package com.sample.api

import com.sample.api.model.ErrorResponse
import com.sample.api.model.Patient
import com.sample.utils.extensions.awaitOutcome

class ApiRepository(apiFactory: ApiFactory) {
    private val api = apiFactory.createApi()
    suspend fun loginDoctor(): Outcome<List<Patient>, ErrorResponse> =
        api.loginDoctor().awaitOutcome()
}