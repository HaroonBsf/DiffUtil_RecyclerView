package com.example.diffutilsrecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteExploreModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import com.example.diffutilsrecyclerview.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnsplashViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    val unsplashImagesData: LiveData<List<UnsplashPhoto?>> = dataRepository.unsplashImagesData
    val unsplashSearchData: LiveData<RemoteExploreModel?> = dataRepository.unsplashSearchData

    fun getUnsplashImagesData(page: Int, per_page: Int) {
        viewModelScope.launch {
            dataRepository.fetchUnsplashData(page, per_page)
        }
    }

    fun getUnsplashSearchData(query: String){
        viewModelScope.launch {
            dataRepository.fetchUnsplashSearchData(query)
        }
    }
}