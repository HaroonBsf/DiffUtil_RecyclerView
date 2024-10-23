package com.example.diffutilsrecyclerview.ui.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diffutilsrecyclerview.data.models.JsonResponse
import com.example.diffutilsrecyclerview.repository.DataRepository
import com.example.diffutilsrecyclerview.data.models.LocalUser
import kotlinx.coroutines.launch

class UserViewModel(private val repository: DataRepository) : ViewModel() {

    val userData: LiveData<JsonResponse?> = repository.userData
    val localUserData: LiveData<List<LocalUser>> = repository.fetchUsersFromRoom()

    @SuppressLint("NullSafeMutableLiveData")
    fun getUserData() {
        viewModelScope.launch {
            repository.fetchUserData()
            /*val response = repository.fetchUserData()
            if (response != null) {
                _userData.postValue(response)
            } else {
                val localUsers = repository.fetchUsersFromRoom()
                _localUserData.postValue(localUsers)
            }*/
        }
    }
}