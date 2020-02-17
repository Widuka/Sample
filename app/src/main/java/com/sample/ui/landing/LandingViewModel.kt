package com.sample.ui.landing

import com.sample.navigation.NavDispatcher
import com.sample.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class LandingViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher)