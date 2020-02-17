package com.sample.model

import android.os.Parcelable
import com.sample.database.model.Patient
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Patient(
    val id: Int,
    val name: String
) : Parcelable {
    constructor(patient: Patient) : this(
        patient.id,
        patient.name
    )

    constructor(patient: com.sample.api.model.Patient) : this(
        patient.id,
        patient.name
    )
}