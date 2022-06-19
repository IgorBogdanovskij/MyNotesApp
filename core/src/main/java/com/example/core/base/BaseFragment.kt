package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.core.model.ToolbarSettings

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    protected open val viewModel: VM? = null

    private var _binding: VB? = null
    protected val binding get() = requireNotNull(_binding)

    protected var toolbarSettings: ToolbarSettings = ToolbarSettings()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initToolbar()
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.let { onBindViewModel(it) }
    }

    private fun initToolbar() {
        onBindToolbar(toolbarSettings)
        (activity as OnSetupToolbarCallback).onSetupToolbar(toolbarSettings)
    }

    open fun onBindViewModel(viewModel: VM) {}

    abstract fun onBindToolbar(settings: ToolbarSettings)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
