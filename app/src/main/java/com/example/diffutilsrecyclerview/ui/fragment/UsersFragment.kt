package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.data.models.localDataModels.users
import com.example.diffutilsrecyclerview.databinding.FragmentUsersBinding
import com.example.diffutilsrecyclerview.ui.adapters.UsersAdapter
import com.example.diffutilsrecyclerview.ui.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment : Fragment() {

    @Inject lateinit var adapter: UsersAdapter
    private val viewModel: UserViewModel by viewModels()

    var _binding : FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewSetup()
        observeRemoteUsers()
        viewModel.getUserData()
    }

    private fun recyclerViewSetup() {
        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(requireContext())
            rvUsers.adapter = adapter
        }
    }

    private fun observeRemoteUsers() {
        viewModel.userData.observe(viewLifecycleOwner, Observer { response ->
            if (response != null){
                adapter.updateUsers(response.users)
            } else{
                observeLocalUsers()
            }
        })
    }

    private fun observeLocalUsers() {
        viewModel.localUserData.observe(viewLifecycleOwner, Observer { localUsers ->
            localUsers?.let {
                val userList = it.users()
                adapter.updateUsers(userList)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}