package com.moviedb.tvshows.view

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.stylekit.ui.hide
import com.moviedb.stylekit.ui.show
import com.moviedb.tvshows.R
import com.moviedb.tvshows.databinding.TvDetailsFragmentBinding
import com.moviedb.tvshows.viewmodel.DetailsViewModel
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DetailsFragment : BaseViewModelFragment<TvDetailsFragmentBinding, DetailsViewModel>(),
    Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieid: String
    val ARG_MOVIEID = "tv_id"

    override fun getLayout(): Int {
        return R.layout.tv_details_fragment
    }

    override fun initView() {
        viewModel = injectViewModel(viewModelFactory)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.id = movieid
        subscribeUi(binding)
        setHasOptionsMenu(true)
    }

    override fun navigateTo(pageName: String, bundle: Bundle) {
        //Do nothing
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(ARG_MOVIEID)?.let {
            movieid = it
        }
    }

    private fun subscribeUi(binding: TvDetailsFragmentBinding) {
        viewModel.getMovieDetails().observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                DataWrapper.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let {
                        binding.movie = it
                    }
                }
                DataWrapper.Status.LOADING -> binding.progressBar.show()
                DataWrapper.Status.ERROR -> {
                    binding.progressBar.hide()
                }
            }
        })
    }
}
