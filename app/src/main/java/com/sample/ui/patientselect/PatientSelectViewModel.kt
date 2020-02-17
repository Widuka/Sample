package com.sample.ui.patientselect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sample.database.repository.PatientRepository
import com.sample.model.Patient
import com.sample.navigation.NavDispatcher
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class PatientSelectViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher,
    patientRepository: PatientRepository,
    private val doctorPatients: List<Patient>?
) : BaseViewModel(backgroundDispatcher) {
    val patients : LiveData<List<Patient>> = if (doctorPatients != null) {
        MutableLiveData<List<Patient>>().apply {
            postValue(doctorPatients)
        }
    } else {
        Transformations.map(patientRepository.patients) { list ->
            list.map { Patient(it) }
        }
    }

    fun navigateBack() = navDispatcher.navigateBack(this)
}