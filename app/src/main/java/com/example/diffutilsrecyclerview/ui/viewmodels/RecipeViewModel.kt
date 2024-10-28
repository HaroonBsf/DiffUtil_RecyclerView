package com.example.diffutilsrecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModel
import com.example.diffutilsrecyclerview.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(val repository: DataRepository): ViewModel(){

    val recipeData: LiveData<RemoteRecipeModel?> = repository.recipeData

    fun getRecipeData() {
        viewModelScope.launch {
            repository.fetchRecipesData()
        }
    }

}