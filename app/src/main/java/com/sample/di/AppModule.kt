package com.sample.di

import com.sample.MainViewModel
import com.sample.SampleApp
import com.sample.api.ApiFactory
import com.sample.api.ApiRepository
import com.sample.api.RetrofitInterceptor
import com.sample.database.SampleDatabase
import com.sample.database.repository.PatientRepository
import com.sample.model.Patient
import com.sample.navigation.NavDispatcher
import com.sample.ui.doctorlogin.DoctorLogInViewModel
import com.sample.ui.landing.LandingViewModel
import com.sample.ui.patientselect.PatientSelectViewModel
import com.sample.ui.splash.SplashViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.experimental.builder.single

fun SampleApp.startKoin() {
    startKoin {
        androidLogger()
        androidContext(this@startKoin)
        modules(listOf(appModule, databaseModule, backendModule))
    }
}

val appModule = module {
    single { Dispatchers.IO }
    single<NavDispatcher>()
    viewModel<MainViewModel>()
    viewModel<SplashViewModel>()
    viewModel<LandingViewModel>()
    viewModel { (doctorsPatients: List<Patient>?) ->
        PatientSelectViewModel(
            get(),
            get(),
            get(),
            doctorsPatients
        )
    }
    viewModel<DoctorLogInViewModel>()
}

val databaseModule = module {
    single { SampleDatabase.invoke(androidContext()) }
    single { get<SampleDatabase>().patientDao() }
    single { PatientRepository(get()) }
}

val backendModule = module {
    single<RetrofitInterceptor>()
    single<ApiFactory>()
    single { ApiRepository(get()) }
}