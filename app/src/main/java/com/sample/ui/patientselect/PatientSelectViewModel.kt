package com.sample.ui.patientselect

import androidx.lifecycle.Transformations
import com.sample.database.repository.PatientRepository
import com.sample.model.Patient
import com.sample.navigation.NavDispatcher
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class PatientSelectViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher,
    patientRepository: PatientRepository
) : BaseViewModel(backgroundDispatcher) {
    val patients = Transformations.map(patientRepository.patients) { list ->
        list.map { Patient(it) }
    }

    fun navigateBack() = navDispatcher.navigateBack(this)
}