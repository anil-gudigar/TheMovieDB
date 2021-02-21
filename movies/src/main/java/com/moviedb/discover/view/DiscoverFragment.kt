package com.moviedb.discover.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.navigation.Navigation
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.discover.R
import com.moviedb.discover.databinding.FragmentDiscoverBinding
import com.moviedb.discover.view.adapters.DiscoverMovieAdapter
import com.moviedb.discover.viewmodel.DiscoverViewModel
import com.moviedb.stylekit.ui.GridItemDecoration
import com.moviedb.stylekit.ui.hide
import com.moviedb.stylekit.ui.show
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverFragment : BaseViewModelFragment<FragmentDiscoverBinding, DiscoverViewModel>(), DiscoverMovieAdapter.DiscoverMovieClickListener
        , Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayout(): Int {
        return R.layout.fragment_discover
    }

    override fun initView() {
        viewModel = injectViewModel(viewModelFactory)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        val adapter = DiscoverMovieAdapter(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.addItemDecoration(GridItemDecoration(0, 2))
        binding.recyclerView.adapter = adapter
        subscribeUi(binding, adapter)
        setHasOptionsMenu(true)
    }

    override fun navigateTo(pageName: String, bundle: Bundle) {
        when (pageName) {
            Navigation.ScreenName.MOVIE_DETAILS -> {
                findNavController().navigate(R.id.action_nav_details, bundle)
            }
        }
    }

    private fun subscribeUi(binding: FragmentDiscoverBinding, adapter: DiscoverMovieAdapter) {
        lifecycleScope.launch {
            viewModel.getMovies().collectLatest { pagingData ->
                binding.progressBar.hide()
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onDiscoverMovieClicked(item: Bundle) {
        navigateTo(Navigation.ScreenName.MOVIE_DETAILS, item)
    }
}
