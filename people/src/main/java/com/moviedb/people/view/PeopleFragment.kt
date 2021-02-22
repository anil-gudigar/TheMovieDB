package com.moviedb.people.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.moviedb.core.di.Injectable
import com.moviedb.core.di.injectViewModel
import com.moviedb.core.navigation.Navigation
import com.moviedb.core.view.BaseViewModelFragment
import com.moviedb.people.R
import com.moviedb.people.databinding.PeopleFragmentBinding
import com.moviedb.people.view.adapter.DiscoverPeopleAdapter
import com.moviedb.people.viewmodel.PeopleViewModel
import com.moviedb.stylekit.ui.GridItemDecoration
import com.moviedb.stylekit.ui.hide
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeopleFragment : BaseViewModelFragment<PeopleFragmentBinding, PeopleViewModel>(), DiscoverPeopleAdapter.DiscoverMovieClickListener
        , Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayout(): Int {
        return R.layout.people_fragment
    }

    override fun initView() {
        viewModel = injectViewModel(viewModelFactory)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        val adapter = DiscoverPeopleAdapter(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.addItemDecoration(GridItemDecoration(0, 2))
        binding.recyclerView.adapter = adapter
        subscribeUi(binding, adapter)
        setHasOptionsMenu(true)
    }

    override fun navigateTo(pageName: String, bundle: Bundle) {
        when (pageName) {
            Navigation.ScreenName.PEOPLE_DETAILS -> {
                findNavController().navigate(R.id.action_people_nav_details, bundle)
            }
        }
    }

    private fun subscribeUi(binding: PeopleFragmentBinding, adapter: DiscoverPeopleAdapter) {
        lifecycleScope.launch {
            viewModel.getPeople().collectLatest { pagingData ->
                binding.progressBar.hide()
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onDiscoverMovieClicked(item: Bundle) {
        navigateTo(Navigation.ScreenName.PEOPLE_DETAILS, item)
    }
}
