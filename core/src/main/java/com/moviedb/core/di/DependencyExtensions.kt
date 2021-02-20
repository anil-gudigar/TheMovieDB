package com.moviedb.core.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Anil Gudigar on 20,February,2021
 */
/**
 * Kotlin extensions for dependency injection
 */
inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(requireActivity(), factory)[T::class.java]
}
