package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.databinding.FragmentUserDetailBinding
import com.example.diffutilsrecyclerview.util.userData

class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        val data = userData
        binding.userDetails = data
//        binding.back.setOnClickListener { findNavController().navigateUp() }
        binding.fbAddRecipe.setOnClickListener {
            Toast.makeText(requireContext(), "Adding Data...", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}