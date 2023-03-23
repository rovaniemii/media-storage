package com.hy0417sage.mediastorage

import android.app.Application
import com.hy0417sage.mediastorage.presentation.views.SharedPreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {
    companion object {
        lateinit var sharedPreference: SharedPreference
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreference = SharedPreference(applicationContext)
    }
}