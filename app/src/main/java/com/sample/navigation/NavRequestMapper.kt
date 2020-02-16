package com.sample.navigation

import com.sample.ui.BaseFragment
import com.sample.ui.splash.SplashFragment

fun NavRequest.toFragmentCreator(): () -> BaseFragment {
    return when (this) {
        is NavRequest.Main -> toFragmentCreator()
    }
}

fun NavRequest.Main.toFragmentCreator(): () -> BaseFragment {
    return when (this) {
        NavRequest.Main.Splash -> SplashFragment.creator()
    }
}