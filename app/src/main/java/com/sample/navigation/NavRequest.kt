package com.sample.navigation

import com.sample.R

sealed class NavRequest {
    abstract val tag: String
    abstract val containerId: Int

    open var navigationMode: NavigationMode = NavigationMode.ADD

    sealed class Main(override val tag: String) : NavRequest() {
        override val containerId = R.id.container

        object Splash : Main("splash")
    }
}