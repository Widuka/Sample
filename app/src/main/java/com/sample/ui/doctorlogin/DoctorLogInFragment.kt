package com.sample.ui.doctorlogin

import android.os.Bundle
import android.view.View
import com.sample.R
import com.sample.ui.BaseFragment
import com.sample.utils.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class DoctorLogInFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_doctor_login
    override val viewModel: DoctorLogInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        viewModel.performLogin()
    }

    private fun observeLiveData() {
        viewModel.loginFailed.observe(viewLifecycleOwner) {
            // show failure info
        }
    }

    override fun onBackPressed() = viewModel.navigateBack()

    companion object {
        fun creator(): () -> DoctorLogInFragment = ::DoctorLogInFragment
    }
}