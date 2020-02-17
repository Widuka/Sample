package com.sample.ui.patientselect.adapter

import android.view.ViewGroup
import com.sample.model.Patient
import com.sample.utils.adapters.BaseAdapter

class PatientAdapter(
    onClick: (Patient) -> Unit
) : BaseAdapter<Patient, PatientViewHolder>(onClick = onClick) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder = PatientViewHolder(parent)
}