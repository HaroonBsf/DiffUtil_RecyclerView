package com.example.diffutilsrecyclerview.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diffutilsrecyclerview.repository.DataRepository

class UserViewModelFactory(private val repository: DataRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            UserViewModel(repository) as T
        } else{
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}