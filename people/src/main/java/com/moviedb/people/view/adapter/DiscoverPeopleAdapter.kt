package com.moviedb.people.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviedb.people.databinding.ListItemPeopleBinding
import com.moviedb.people.domian.model.People

/**
 * Created by Anil Gudigar on 21,February,2021
 */
/**
 * Adapter for the [RecyclerView] in [DiscoverFragment].
 */
class DiscoverPeopleAdapter(val listener: DiscoverMovieClickListener) :
        PagingDataAdapter<People, DiscoverPeopleAdapter.ViewHolder>(DiffCallback()) {
    val ARG_PEOPLEID = "people_id"

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val people = getItem(position)
        holder.apply {
            if (people != null) {
                bind(createOnClickListener(people.id.toString()), people)
            }
            itemView.tag = people
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ListItemPeopleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {
            val args = Bundle()
            args.putString(ARG_PEOPLEID, id)
            listener.onDiscoverMovieClicked(args)
        }
    }

    class ViewHolder(
            private val binding: ListItemPeopleBinding
    ) : RecyclerView.ViewHolder(binding.moiveItem) {
        fun bind(listener: View.OnClickListener, item: People) {
            binding.apply {
                clickListener = listener
                this.people = item
            }
        }
    }

    interface DiscoverMovieClickListener {
        fun onDiscoverMovieClicked(item: Bundle)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<People>() {

    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem == newItem
    }
}