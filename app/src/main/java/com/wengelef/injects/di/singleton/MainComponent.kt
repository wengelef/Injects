package com.wengelef.injects.di.singleton

import com.wengelef.injects.MainApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun coffee(): Coffee
}