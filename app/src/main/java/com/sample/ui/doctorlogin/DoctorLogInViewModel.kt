package com.sample.ui.doctorlogin

import com.sample.navigation.NavDispatcher
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class DoctorLogInViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
):BaseViewModel(backgroundDispatcher){
    fun navigateBack() = navDispatcher.navigateBack(this)
}