package com.op.inc.mymovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyMoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}