package com.wengelef.injects.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wengelef.inject.annotation.Injects
import com.wengelef.injects.MainApplication
import com.wengelef.injects.R
import com.wengelef.injects.di.view.CoffeeMachine
import com.wengelef.injects.di.view.DaggerViewComponent
import com.wengelef.injects.di.view.ViewComponent
import javax.inject.Inject

@Injects(ViewComponent::class)
class MainFragment : Fragment() {

    @Inject
    lateinit var coffeeMachine: CoffeeMachine

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fr_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        val mainComponent = ((activity!!.application) as MainApplication).mainComponent

        val viewComponent = DaggerViewComponent
            .builder()
            .mainComponent(mainComponent)
            .build()

        viewComponent.inject(this)
    }

}
