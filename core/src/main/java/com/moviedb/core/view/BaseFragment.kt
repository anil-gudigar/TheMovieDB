package com.moviedb.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by Anil Gudigar on 21,February,2021
 */
abstract class BaseFragment : Fragment() {

    companion object {
        var TAG = "BaseFragment"
    }

    protected val fragmentArguments: Bundle?
        get() = arguments


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TAG = this.javaClass.simpleName
        return bindView(inflater, container)
    }

    protected abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): View

    fun navigate(screenName: String, bundle: Bundle) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).navigate(screenName, bundle)
        }
    }

    fun hideBottomNav() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideBottomNav()
        }
    }

    fun showBottomNav() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showBottomNav()
        }
    }
}