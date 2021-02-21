package com.moviedb.discover.view

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.navigation.Navigation
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.discover.R
import com.moviedb.discover.databinding.DetailsFragmentBinding
import com.moviedb.discover.viewmodel.DetailsViewModel
import com.moviedb.stylekit.ui.hide
import com.moviedb.stylekit.ui.show
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DetailsFragment : BaseViewModelFragment<DetailsFragmentBinding, DetailsViewModel>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieid: String
    val ARG_MOVIEID = "movieid"

    override fun getLayout(): Int {
        return R.layout.details_fragment
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

    private fun subscribeUi(binding: DetailsFragmentBinding) {
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
