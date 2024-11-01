package com.example.diffutilsrecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalRecipeModel
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalUser
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModel
import com.example.diffutilsrecyclerview.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class RecipeViewModel @Inject constructor(val repository: DataRepository): ViewModel(){

    val recipeData: LiveData<RemoteRecipeModel?> = repository.recipeData
    val localRecipesData: LiveData<List<LocalRecipeModel>> = repository.fetchRecipesFromRoom()

    fun getRecipeData() {
        viewModelScope.launch {
            repository.fetchRecipesData()
        }
    }
}