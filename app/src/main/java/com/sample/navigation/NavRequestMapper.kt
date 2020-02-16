package com.sample.navigation

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