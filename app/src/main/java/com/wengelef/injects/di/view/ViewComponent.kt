package com.wengelef.injects.di.view

import com.wengelef.injects.di.singleton.MainComponent
import com.wengelef.injects.ui.main.MainFragment
import dagger.Component
import javax.inject.Scope

@Scope annotation class ViewScope

@ViewScope
@Component(dependencies = [MainComponent::class], modules = [ViewModule::class])
interface ViewComponent : ViewComponentInjectors