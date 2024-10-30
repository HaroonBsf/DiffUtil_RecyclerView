package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.ImageModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteExploreModel
import com.example.diffutilsrecyclerview.databinding.FragmentExploreBinding
import com.example.diffutilsrecyclerview.ui.adapters.UnsplashAdapter
import com.example.diffutilsrecyclerview.util.API_KEY_UNSPLASH
import com.example.diffutilsrecyclerview.util.BASE_URL_UNSPLASH
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ExploreFragment : Fragment() {

    @Inject
    lateinit var adapter: UnsplashAdapter

    var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSetup()
        getData()

    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_UNSPLASH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val response = retrofit.getUnsplashImages(API_KEY_UNSPLASH, 1, 30)
        response.enqueue(object : Callback<ImageModel?> {
            override fun onResponse(
                p0: Call<ImageModel?>,
                response: Response<ImageModel?>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    adapter.updateImages(listOf(body))
                }
            }

            override fun onFailure(p0: Call<ImageModel?>, p1: Throwable) {

            }
        })
    }

    private fun rvSetup() {
        binding.rvExploreImages.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvExploreImages.setHasFixedSize(true)
        binding.rvExploreImages.adapter = adapter
    }
}