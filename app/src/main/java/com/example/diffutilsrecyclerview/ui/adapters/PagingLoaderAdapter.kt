package com.example.diffutilsrecyclerview.ui.adapters

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.databinding.PagingLoaderItemBinding

class PagingLoaderAdapter : LoadStateAdapter<> {

    class PagingViewHolder(val binding: PagingLoaderItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState) {
            binding.progressPaging.isVisible = loadState is LoadState.Loading
        }
    }
}