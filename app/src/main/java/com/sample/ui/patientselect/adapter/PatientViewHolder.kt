package com.sample.ui.patientselect.adapter

import android.view.View
import android.view.ViewGroup
import com.sample.R
import com.sample.model.Patient
import com.sample.utils.adapters.BaseViewHolder
import com.sample.utils.extensions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.patient_item.view.*

class PatientViewHolder private constructor(override val containerView: View) :
    BaseViewHolder<Patient>(containerView),
    LayoutContainer {

    constructor(parent: ViewGroup) : this(parent.inflate(R.layout.patient_item))

    override fun bind(item: Patient, onClick: ((Patient) -> Unit)?) {
        with(containerView) {
            textViewPatient.text = item.name
            setOnClickListener { onClick?.invoke(item) }
        }
    }
}