package com.sample.di

import com.sample.MainViewModel
import com.sample.SampleApp
import com.sample.navigation.NavDispatcher
import com.sample.ui.splash.SplashViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.experimental.builder.single

fun SampleApp.startKoin() {
    startKoin {
        androidLogger()
        androidContext(this@startKoin)
        modules(listOf(appModule))
    }
}

val appModule = module {
    single { Dispatchers.IO }
    single<NavDispatcher>()
    viewModel<MainViewModel>()
    viewModel<SplashViewModel>()
}