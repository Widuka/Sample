package com.sample

import com.sample.navigation.NavDispatcher
import com.sample.navigation.NavRequest
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher) {
    val navigationChanges = navDispatcher.navigationChanges

    private suspend fun navigate(navRequest: NavRequest) = navDispatcher.navigate(navRequest)

    fun openSplashScreen() = launch {
        navigate(NavRequest.Main.Splash)
    }
}