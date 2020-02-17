package com.sample.ui.landing

import com.sample.navigation.NavDispatcher
import com.sample.navigation.NavRequest
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class LandingViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher){
    fun openDoctorLogInFragment() = launch {
        navDispatcher.navigate(NavRequest.Main.DoctorLogIn)
    }

    fun openPatientSelectFragment() = launch {
        navDispatcher.navigate(NavRequest.Main.PatientSelect)
    }
}