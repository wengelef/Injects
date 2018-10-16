package com.wengelef.injects

import android.app.Application
import com.wengelef.injects.di.singleton.DaggerMainComponent
import com.wengelef.injects.di.singleton.MainComponent

class MainApplication : Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.create()
    }
}