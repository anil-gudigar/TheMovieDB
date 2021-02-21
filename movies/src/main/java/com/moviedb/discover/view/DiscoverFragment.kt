package com.moviedb.discover.view

import androidx.lifecycle.ViewModelProvider
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.discover.R
import com.moviedb.discover.databinding.FragmentDiscoverBinding
import com.moviedb.discover.viewmodel.DiscoverViewModel
import javax.inject.Inject

class DiscoverFragment : BaseViewModelFragment<FragmentDiscoverBinding, DiscoverViewModel>(),
    Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayout(): Int {
        return R.layout.fragment_discover
    }

    override fun initView() {
        viewModel = injectViewModel(viewModelFactory)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}
