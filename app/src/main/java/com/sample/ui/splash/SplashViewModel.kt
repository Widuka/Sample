package com.sample.ui.splash

import com.sample.navigation.NavDispatcher
import com.sample.navigation.NavRequest
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher) {
    fun openUserSelectFragment() = launch {
        delay(SPLASH_DURATION)
        navDispatcher.navigate(NavRequest.Main.Landing)
    }

    companion object {
        const val SPLASH_DURATION = 1000L
    }
}