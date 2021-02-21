package com.moviedb.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.moviedb.core.viewmodel.BaseViewModel

/**
 * Created by Anil Gudigar on 21,February,2021
 */
abstract class BaseViewModelFragment<B : ViewDataBinding, VM : BaseViewModel> : BaseFragment() {
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected var bundle: Bundle? = null

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        return binding.root
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<B>(inflater, getLayout(), container, false)
        this.binding = dataBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readBundle()
        initView()
    }

    abstract fun getLayout(): Int

    abstract fun initView()

    protected fun readBundle() {
        bundle = this.arguments
    }
}