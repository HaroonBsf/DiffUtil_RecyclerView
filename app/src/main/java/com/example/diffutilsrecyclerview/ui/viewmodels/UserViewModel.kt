package com.example.diffutilsrecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diffutilsrecyclerview.repository.DataRepository
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalUser
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteUsersModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    val userData: LiveData<RemoteUsersModel?> = repository.userData
    val localUserData: LiveData<List<LocalUser>> = repository.fetchUsersFromRoom()

    fun getUserData() {
        viewModelScope.launch {
            repository.fetchUserData()
        }
    }
}