package com.moviedb.more.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.more.R
import com.moviedb.more.databinding.MoreFragmentBinding
import com.moviedb.more.domain.model.MoreModel
import com.moviedb.more.view.adapters.MoreAdapter
import com.moviedb.more.viewmodel.MoreViewModel
import javax.inject.Inject

class MoreFragment : BaseViewModelFragment<MoreFragmentBinding, MoreViewModel>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter: MoreAdapter

    override fun getLayout(): Int {
        return R.layout.more_fragment
    }

    override fun initView() {
        viewModel = injectViewModel(viewModelFactory)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        adapter = context?.let { MoreModel.getMoreList(it) }
            ?.let { MoreAdapter(it) } ?: MoreAdapter(arrayListOf())
        binding.rvMore.adapter = adapter
    }

    override fun navigateTo(pageName: String, bundle: Bundle) {
        //Do nothing
    }

}