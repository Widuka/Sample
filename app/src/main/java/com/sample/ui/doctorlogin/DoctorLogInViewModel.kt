package com.sample.ui.doctorlogin

import com.sample.api.ApiRepository
import com.sample.api.Outcome
import com.sample.model.Patient
import com.sample.navigation.NavDispatcher
import com.sample.navigation.NavRequest
import com.sample.ui.BaseViewModel
import com.sample.utils.LiveEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DoctorLogInViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher,
    private val apiRepository: ApiRepository
):BaseViewModel(backgroundDispatcher){
    val loginFailed = LiveEvent<String?>()

    fun performLogin() = launch {
        when (val result = apiRepository.loginDoctor()) {
            is Outcome.Success -> {
                openPatientSelectFragment(result.data.map { Patient(it) })
            }
            is Outcome.Failure -> {
                result.failureBody.error?.let {
                    loginFailed.postValue(it)
                }
            }
            is Outcome.Error -> {
                loginFailed.postValue(null)
            }
        }
    }

    private fun openPatientSelectFragment(doctorsPatients: List<Patient>) = launch {
        navDispatcher.navigate(NavRequest.Main.PatientSelect(doctorsPatients))
    }

    fun navigateBack() = navDispatcher.navigateBack(this)
}