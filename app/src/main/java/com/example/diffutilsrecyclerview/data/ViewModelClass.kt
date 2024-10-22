package com.example.diffutilsrecyclerview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diffutilsrecyclerview.model.JsonResponse
import kotlinx.coroutines.launch

class ViewModelClass(private val repository: DataRepository) : ViewModel() {

    private val _userData = MutableLiveData<JsonResponse>()
    val userData: LiveData<JsonResponse> get() = _userData

    fun getUserData() {
        viewModelScope.launch {
            val response = repository.fetchUserData()
            response?.let {
                _userData.postValue(it)
            }
        }
    }

}