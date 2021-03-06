package com.cuncisboss.rickandmorty.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.cuncisboss.rickandmorty.BR

abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel<out Any>>: Fragment() {

    private lateinit var dataBinding: T

    @LayoutRes
    abstract fun setLayout(): Int

    abstract fun getViewModel(): V

    private val baseViewModel by lazy { getViewModel() }

    open fun onInitialization() = Unit

    open fun onReadyAction() = Unit

    open fun onObserveAction() = Unit

    open fun getViewDataBinding() = dataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, setLayout(), container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.setVariable(BR._all, baseViewModel)
        onInitialization()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onReadyAction()
        onObserveAction()
    }
}