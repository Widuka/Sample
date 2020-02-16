package com.sample.navigation

import com.sample.MainActivity

data class Navigation(private val action: NavigationAction) {
    private var consumed = false

    fun executeIfNotConsumed(mainActivity: MainActivity) {
        if (!consumed) {
            action(mainActivity)
            consumed = true
        }
    }
}

typealias NavigationAction = (MainActivity) -> Unit
