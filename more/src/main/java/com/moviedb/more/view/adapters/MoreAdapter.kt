package com.moviedb.more.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviedb.more.R
import com.moviedb.more.databinding.ItemMoreListBinding
import com.moviedb.more.domain.model.MoreModel

/**
 * Created by Anil Gudigar on 22,February,2021
 */
class MoreAdapter(var moreList: List<MoreModel>) :
    RecyclerView.Adapter<MoreAdapter.MoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMoreListBinding>(
            layoutInflater,
            R.layout.item_more_list,
            parent,
            false
        )
        return MoreViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = moreList.size

    override fun onBindViewHolder(viewHolder: MoreViewHolder, position: Int) =
        viewHolder.bind(moreList[position])

    class MoreViewHolder(
        private val binding: ItemMoreListBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moreModel: MoreModel) {
            binding.moreItem = moreModel
            binding.tvSubtext.setTextColor(ContextCompat.getColor(context, moreModel.colorRes))
        }
    }
}

@BindingAdapter("setImage")
fun imageResource(imageView: ImageView, @DrawableRes resource: Int) {
    imageView.setImageResource(resource)
}