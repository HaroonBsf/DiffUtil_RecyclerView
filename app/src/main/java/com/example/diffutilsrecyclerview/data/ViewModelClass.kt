package com.example.diffutilsrecyclerview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.diffutilsrecyclerview.model.JsonResponse
import com.example.diffutilsrecyclerview.model.User
import kotlinx.coroutines.launch

class ViewModelClass(private val repository: DataRepository) : ViewModel() {

    //val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()
    private val _userData = MutableLiveData<JsonResponse>()
    val userData: LiveData<JsonResponse> get() = _userData

    /*fun fetchFromApi(){
        viewModelScope.launch {
            repository.fetchUserData()
        }
    }*/

    fun getUserData() {
        viewModelScope.launch {
            val response = repository.fetchUserData()
            response?.let {
                _userData.postValue(it)
            }
        }
    }

}