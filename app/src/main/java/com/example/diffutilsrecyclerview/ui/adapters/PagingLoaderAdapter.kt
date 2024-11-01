package com.example.diffutilsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.databinding.PagingLoaderItemBinding

class PagingLoaderAdapter : LoadStateAdapter<PagingLoaderAdapter.LoaderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding = PagingLoaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoaderViewHolder(val binding: PagingLoaderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.progressPaging.isVisible = loadState is LoadState.Loading
        }
    }
}