package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.databinding.FragmentFullImageBinding
import com.example.diffutilsrecyclerview.util.imageUrl

class FullImageFragment : Fragment() {

    private var _binding : FragmentFullImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullImageBinding.inflate(inflater, container, false)
        val image = imageUrl
        binding.apply {
            fullImage = image
            back.setOnClickListener { findNavController().navigate(R.id.action_fullImageFragment_to_homeFragment) }
        }

        return binding.root
    }
}