package com.moviedb.discover.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.discover.R
import com.moviedb.discover.databinding.FragmentDiscoverBinding
import com.moviedb.discover.view.adapters.DiscoverMovieAdapter
import com.moviedb.discover.viewmodel.DiscoverViewModel
import com.moviedb.stylekit.ui.GridItemDecoration
import com.moviedb.stylekit.ui.hide
import com.moviedb.stylekit.ui.show
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
        val adapter = DiscoverMovieAdapter()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        binding.recyclerView.addItemDecoration(GridItemDecoration(0, 2))
        binding.recyclerView.adapter = adapter

        subscribeUi(binding, adapter)

        setHasOptionsMenu(true)
    }

    private fun subscribeUi(binding: FragmentDiscoverBinding, adapter: DiscoverMovieAdapter) {
        viewModel.getMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                DataWrapper.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let {
                        adapter.submitList(it)
                    }
                }
                DataWrapper.Status.LOADING -> binding.progressBar.show()
                DataWrapper.Status.ERROR -> {
                    binding.progressBar.hide()
                    //Snackbar.make(binding.discoverLayout, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}
