package com.example.diffutilsrecyclerview.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val repository: DataRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModelClass::class.java)){
            ViewModelClass(repository) as T
        } else{
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

}