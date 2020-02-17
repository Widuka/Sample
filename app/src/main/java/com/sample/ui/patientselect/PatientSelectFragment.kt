package com.sample.ui.patientselect

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.sample.R
import com.sample.model.Patient
import com.sample.ui.BaseFragment
import com.sample.ui.patientselect.adapter.PatientAdapter
import com.sample.utils.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PatientSelectFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_patient_select
    override val viewModel: PatientSelectViewModel by viewModel { parametersOf(arguments?.getParcelableArrayList<Patient>(KEY_PATIENTS)) }

    private val patientAdapter = PatientAdapter {
        // do some action
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() = viewModel.patients.observe(viewLifecycleOwner) {
        patientAdapter.submitList(it)
    }

    override fun onBackPressed() = viewModel.navigateBack()

    companion object {
        fun creator(doctorsPatients: List<Patient>? = null): () -> PatientSelectFragment = {
            PatientSelectFragment().apply {
                arguments = bundleOf(KEY_PATIENTS to doctorsPatients)
            }
        }

        private const val KEY_PATIENTS = "keyPatients"
    }
}