package com.sample.navigation

import com.sample.R
import com.sample.model.Patient

sealed class NavRequest {
    abstract val tag: String
    abstract val containerId: Int

    open var navigationMode: NavigationMode = NavigationMode.ADD

    sealed class Main(override val tag: String) : NavRequest() {
        override val containerId = R.id.container

        object Splash : Main("splash")
        object Landing : Main("landing")
        data class PatientSelect(val doctorsPatients: List<Patient>? = null) : Main("patientSelect")
        object DoctorLogIn : Main("doctorLogIn")
    }
}