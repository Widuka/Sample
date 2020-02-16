package com.sample.ui.splash

import com.sample.navigation.NavDispatcher
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class SplashViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher)