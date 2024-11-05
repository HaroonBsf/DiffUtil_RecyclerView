package com.example.diffutilsrecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import com.example.diffutilsrecyclerview.repository.DataRepository
import com.example.diffutilsrecyclerview.util.API_KEY_UNSPLASH
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class UnsplashViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>()

    val unsplashImages = dataRepository.fetchUnsplashData().cachedIn(viewModelScope)

    val searchResults: LiveData<PagingData<UnsplashPhoto>> = _searchQuery.switchMap { query ->
        dataRepository.fetchUnsplashSearchImages(API_KEY_UNSPLASH, query).cachedIn(viewModelScope)
    }

    fun searchUnsplashImages(query: String) {
        _searchQuery.value = query
    }
}

/*
@HiltViewModel
class UnsplashViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    private val API_KEY = API_KEY_UNSPLASH
    private val _searchResults = MutableLiveData<LiveData<PagingData<UnsplashPhoto>>>()
    val searchResults: LiveData<PagingData<UnsplashPhoto>> get() = _searchResults.switchMap { it }

    fun searchUnsplashImages(query: String) {
        _searchResults.value = dataRepository.fetchUnsplashSearchImages(API_KEY, query)
    }

    fun getUnsplashImages(): LiveData<PagingData<UnsplashPhoto>> {
        return dataRepository.fetchUnsplashData().cachedIn(viewModelScope)
    }

}*/
