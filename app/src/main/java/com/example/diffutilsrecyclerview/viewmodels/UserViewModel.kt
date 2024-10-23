package com.example.diffutilsrecyclerview.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diffutilsrecyclerview.data.DataRepository
import com.example.diffutilsrecyclerview.model.JsonResponse
import com.example.diffutilsrecyclerview.model.LocalUser
import kotlinx.coroutines.launch

class UserViewModel(private val repository: DataRepository) : ViewModel() {

    private val _userData = MutableLiveData<JsonResponse>()
    val userData: LiveData<JsonResponse> get() = _userData

    private val _localUserData = MutableLiveData<List<LocalUser>>()
    val localUserData: LiveData<List<LocalUser>> get() = _localUserData

    @SuppressLint("NullSafeMutableLiveData")
    fun getUserData() {
        viewModelScope.launch {
            val response = repository.fetchUserData()
            if (response != null) {
                _userData.postValue(response)
            } else {
                val localUsers = repository.fetchUsersFromRoom()
                _localUserData.postValue(localUsers)
            }
        }
    }
}