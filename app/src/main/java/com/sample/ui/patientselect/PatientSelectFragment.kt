package com.sample.ui.patientselect

import com.sample.R
import com.sample.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PatientSelectFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_patient_select
    override val viewModel: PatientSelectViewModel by viewModel()

    override fun onBackPressed() = viewModel.navigateBack()

    companion object {
        fun creator(): () -> PatientSelectFragment = ::PatientSelectFragment
    }
}