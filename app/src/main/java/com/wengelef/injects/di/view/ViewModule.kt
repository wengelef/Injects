package com.wengelef.injects.di.view

import com.wengelef.injects.di.singleton.Coffee
import dagger.Module
import javax.inject.Inject

@Module abstract class ViewModule {
    @ViewScope abstract fun provideCoffeeMachine(coffee: Coffee): CoffeeMachine
}

class CoffeeMachine @Inject constructor(val coffee: Coffee)
