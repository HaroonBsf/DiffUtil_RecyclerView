package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.data.models.localDataModels.users
import com.example.diffutilsrecyclerview.databinding.FragmentUsersBinding
import com.example.diffutilsrecyclerview.ui.adapters.UsersAdapter
import com.example.diffutilsrecyclerview.ui.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalPagingApi
class UsersFragment : Fragment() {

    @Inject
    lateinit var adapter: UsersAdapter
    private val viewModel: UserViewModel by viewModels()

    var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterUsers()
        recyclerViewSetup()
        observeRemoteUsers()
        viewModel.getUserData()
    }

    private fun filterUsers() {
        binding.etSearchUsers.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterUsersNow(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterUsersNow(query: String) {
        val filteredUsers = viewModel.userData.value?.users?.filter {
            val fullName = "${it.firstName} ${it.lastName}"
            fullName.contains(query, ignoreCase = true)
        } ?: emptyList()
        adapter.updateUsers(filteredUsers)
    }

    private fun recyclerViewSetup() {
        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(requireContext())
            rvUsers.adapter = adapter
        }
    }

    private fun observeRemoteUsers() {
        binding.shimmerLayoutUsers.startShimmer()
        viewModel.userData.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                adapter.updateUsers(response.users)
                binding.rvUsers.visibility = View.VISIBLE
                binding.shimmerLayoutUsers.stopShimmer()
                binding.shimmerLayoutUsers.visibility = View.GONE
            } else {
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