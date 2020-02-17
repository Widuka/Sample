package com.sample.ui.landing

import android.os.Bundle
import android.view.View
import com.sample.R
import com.sample.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_landing.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_landing
    override val viewModel: LandingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewListeners()
    }

    private fun setupViewListeners() {
        cardDoctor.setOnClickListener {
            viewModel.openDoctorLogInFragment()
        }
        cardPatient.setOnClickListener {
            viewModel.openPatientSelectFragment()
        }
    }

    companion object {
        fun creator(): () -> LandingFragment = ::LandingFragment
    }
}