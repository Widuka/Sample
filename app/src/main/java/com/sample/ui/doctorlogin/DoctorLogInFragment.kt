package com.sample.ui.doctorlogin

import com.sample.R
import com.sample.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DoctorLogInFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_doctor_login
    override val viewModel: DoctorLogInViewModel by viewModel()

    override fun onBackPressed() = viewModel.navigateBack()

    companion object {
        fun creator(): () -> DoctorLogInFragment = ::DoctorLogInFragment
    }
}