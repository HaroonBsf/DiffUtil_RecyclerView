package com.example.diffutilsrecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModelTwo
import com.example.diffutilsrecyclerview.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRecipeViewModel @Inject constructor(val repository: DataRepository): ViewModel() {

    val topRecipeData: LiveData<RemoteRecipeModelTwo?> = repository.topRecipeData

    fun getTopRecipeData(){
        viewModelScope.launch {
            repository.fetchTopRecipeData()
        }
    }

}