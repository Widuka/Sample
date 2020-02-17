package com.sample.model

import com.sample.database.model.Patient

data class Patient(
    val id: Int,
    val name: String
) {
    constructor(patient: Patient) : this(
        patient.id,
        patient.name
    )
}