package com.example.diffutilsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import com.example.diffutilsrecyclerview.databinding.RvExploreItemBinding
import com.example.diffutilsrecyclerview.util.imageUrl
import javax.inject.Inject

class UnsplashPagingAdapter @Inject constructor() : PagingDataAdapter<UnsplashPhoto, UnsplashPagingAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvExploreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            unsplashImage = currentItem
            executePendingBindings()
            root.setOnClickListener {
                imageUrl = currentItem
                it.findNavController().navigate(R.id.action_homeFragment_to_fullImageFragment)
            }
        }
    }

    class ViewHolder(val binding: RvExploreItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) = oldItem == newItem
        }
    }
}
