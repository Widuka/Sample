package com.sample

import android.app.Application
import com.sample.di.startKoin

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }
}