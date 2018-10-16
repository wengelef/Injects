package com.wengelef.injects.di.singleton

import android.util.Log
import dagger.Module
import javax.inject.Inject
import javax.inject.Singleton

@Module
abstract class MainModule {
    @Singleton abstract fun providesCoffee(): Coffee
}

class Coffee @Inject constructor() {
    init {
        Log.d("Coffee", "::instance")
    }
}
