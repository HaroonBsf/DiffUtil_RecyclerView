package com.example.diffutilsrecyclerview.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diffutilsrecyclerview.viewmodels.UserViewModel

class UserViewModelFactory(private val repository: DataRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            UserViewModel(repository) as T
        } else{
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}