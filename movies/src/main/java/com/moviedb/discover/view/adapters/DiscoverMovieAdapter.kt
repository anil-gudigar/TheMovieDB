package com.moviedb.discover.view.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moviedb.common.domain.model.Movie
import com.moviedb.discover.databinding.ListItemBinding

/**
 * Created by Anil Gudigar on 21,February,2021
 */
/**
 * Adapter for the [RecyclerView] in [DiscoverFragment].
 */
class DiscoverMovieAdapter(val listener: DiscoverMovieClickListener) :
    PagingDataAdapter<Movie, DiscoverMovieAdapter.ViewHolder>(DiffCallback()) {
    val ARG_MOVIEID = "movieid"

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.apply {
            if (movie != null) {
                bind(createOnClickListener(movie.id), movie)
            }
            itemView.tag = movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {
            val args = Bundle()
            args.putString(ARG_MOVIEID, id)
            listener.onDiscoverMovieClicked(args)
        }
    }

    class ViewHolder(
            private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.moiveItem) {
        fun bind(listener: View.OnClickListener, item: Movie) {
            binding.apply {
                clickListener = listener
                this.movie = item
            }
        }
    }

    interface DiscoverMovieClickListener {
        fun onDiscoverMovieClicked(item: Bundle)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}