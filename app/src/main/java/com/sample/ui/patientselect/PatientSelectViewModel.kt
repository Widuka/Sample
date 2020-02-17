package com.sample.ui.patientselect

import com.sample.navigation.NavDispatcher
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class PatientSelectViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher) {
    fun navigateBack() = navDispatcher.navigateBack(this)
}