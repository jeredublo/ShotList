package com.example.shotlist.base_mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

abstract class MVIFragment<State, V : MVIViewModel<State>>(
    private val viewModelClass: Class<V>,
    private val layout: Int
) : Fragment() {
    protected val viewModel: V by lazy {
        activity?.let { ViewModelProvider(it)[viewModelClass] } ?: throw Exception("Illegal Activity")
    }

    abstract fun getInitAction(): BaseAction<State>?
    abstract fun initUI(view: View)
    abstract fun renderUI(newState: State)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getInitAction()?.let { viewModel.performAction(it) }
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, Observer(::renderUI))
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                for (event in viewModel.eventChannel) {
                    event.performEvent(this@MVIFragment)
                }
            }
        }
        initUI(view)
    }
}