package com.moviedb.tvshows.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.navigation.Navigation
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.stylekit.ui.GridItemDecoration
import com.moviedb.stylekit.ui.hide
import com.moviedb.tvshows.R
import com.moviedb.tvshows.databinding.TvShowsFragmentBinding
import com.moviedb.tvshows.view.adapters.TVShowMovieAdapter
import com.moviedb.tvshows.viewmodel.TvShowsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvShowsFragment : BaseViewModelFragment<TvShowsFragmentBinding, TvShowsViewModel>(),
    TVShowMovieAdapter.TVShowMovieClickListener
    , Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayout(): Int {
        return R.layout.tv_shows_fragment
    }

    override fun initView() {
        viewModel = injectViewModel(viewModelFactory)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        val adapter = TVShowMovieAdapter(this)
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

    private fun subscribeUi(binding: TvShowsFragmentBinding, adapter: TVShowMovieAdapter) {
        lifecycleScope.launch {
            viewModel.getMovies().collectLatest { pagingData ->
                binding.progressBar.hide()
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onTVShowMovieClicked(item: Bundle) {
        navigateTo(Navigation.ScreenName.MOVIE_DETAILS, item)
    }
}
