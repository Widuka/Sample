package com.sample.navigation

import com.sample.ui.BaseFragment
import com.sample.ui.doctorlogin.DoctorLogInFragment
import com.sample.ui.landing.LandingFragment
import com.sample.ui.patientselect.PatientSelectFragment
import com.sample.ui.splash.SplashFragment

fun NavRequest.toFragmentCreator(): () -> BaseFragment {
    return when (this) {
        is NavRequest.Main -> toFragmentCreator()
    }
}

fun NavRequest.Main.toFragmentCreator(): () -> BaseFragment {
    return when (this) {
        NavRequest.Main.Splash -> SplashFragment.creator()
        NavRequest.Main.Landing -> LandingFragment.creator()
        is NavRequest.Main.PatientSelect -> PatientSelectFragment.creator(doctorsPatients)
        NavRequest.Main.DoctorLogIn -> DoctorLogInFragment.creator()
    }
}