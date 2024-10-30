package com.example.diffutilsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.ImageModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.Urls
import com.example.diffutilsrecyclerview.databinding.RvExploreItemBinding
import javax.inject.Inject

class UnsplashAdapter @Inject constructor() : RecyclerView.Adapter<UnsplashAdapter.ViewHolder>() {

    private var imagesList = listOf<ImageModel?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvExploreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = imagesList[position]
        holder.binding.unsplashImage = currentItem
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    class ViewHolder(val binding: RvExploreItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateImages(newUserList: List<ImageModel?>) {
        val diffCallback = ImagesDiffCallback(imagesList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        imagesList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}

class ImagesDiffCallback(private val oldList: List<ImageModel?>, private val newList: List<ImageModel?>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}