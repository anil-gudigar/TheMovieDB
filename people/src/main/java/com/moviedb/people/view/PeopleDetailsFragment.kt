package com.moviedb.people.view

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.people.R
import com.moviedb.people.databinding.PeopleDetailsFragmentBinding
import com.moviedb.people.viewmodel.PeopleDetailsViewModel
import com.moviedb.stylekit.ui.hide
import com.moviedb.stylekit.ui.show
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class PeopleDetailsFragment : BaseViewModelFragment<PeopleDetailsFragmentBinding, PeopleDetailsViewModel>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var people_id: String
    val ARG_PEOPLEID = "people_id"

    override fun getLayout(): Int {
        return R.layout.people_details_fragment
    }

    override fun initView() {
        viewModel = injectViewModel(viewModelFactory)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.id = people_id
        subscribeUi(binding)
        setHasOptionsMenu(true)
    }

    override fun navigateTo(pageName: String, bundle: Bundle) {
        //Do nothing
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(ARG_PEOPLEID)?.let {
            people_id = it
        }
    }

    private fun subscribeUi(binding: PeopleDetailsFragmentBinding) {
        viewModel.getPeopleDetails().observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                DataWrapper.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let {
                        binding.people = it
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
