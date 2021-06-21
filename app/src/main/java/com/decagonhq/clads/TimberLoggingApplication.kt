package com.decagonhq.clads

import android.app.Application
import timber.log.Timber

class TimberLoggingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
