package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteExploreModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import com.example.diffutilsrecyclerview.databinding.FragmentExploreBinding
import com.example.diffutilsrecyclerview.ui.adapters.UnsplashAdapter
import com.example.diffutilsrecyclerview.util.API_KEY_UNSPLASH
import com.example.diffutilsrecyclerview.util.BASE_URL_UNSPLASH
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    @Inject
    lateinit var adapter: UnsplashAdapter

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val apiService: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL_UNSPLASH)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

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
//        getInitialData()
//        setupSearch()
        searchImages("food")
    }

    private fun getInitialData() {
        val response = apiService.getUnsplashImages(API_KEY_UNSPLASH, 1, 30)
        response.enqueue(object : Callback<List<UnsplashPhoto>?> {
            override fun onResponse(call: Call<List<UnsplashPhoto>?>, response: Response<List<UnsplashPhoto>?>) {
                if (response.isSuccessful) {
                    val images = response.body() ?: emptyList()
                    Log.d("EXPLORE", "Initial data loaded successfully")
                    adapter.updateImages(images)
                } else {
                    Log.e("EXPLORE", "Failed to load initial data: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<UnsplashPhoto>?>, t: Throwable) {
                Log.e("EXPLORE", "Failed to load initial data: ${t.message}")
            }
        })
    }

    private fun setupSearch() {
        binding.etSearchImages.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isNotEmpty()) {
                    searchImages(query)
                } else {
                    getInitialData()
                }
            }
        })
    }

    private fun searchImages(query: String) {
        apiService.getUnsplashSearchImages(API_KEY_UNSPLASH, query)
            .enqueue(object : Callback<RemoteExploreModel?> {
                override fun onResponse(call: Call<RemoteExploreModel?>, response: Response<RemoteExploreModel?>) {
                    if (response.isSuccessful) {
                        val searchResults = response.body()?.results ?: emptyList()
                        for (photos in searchResults) {
                            Log.d("EXPLORE", "Photo URL: ${photos.urls.regular}")
//                            Search data loaded successfully
                        }
                        adapter.updateImages(searchResults)
                    } else {
                        Log.e("EXPLORE", "Search failed: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<RemoteExploreModel?>, t: Throwable) {
                    Log.e("EXPLORE", "Failed to load search data: ${t.message}")
                }
            })
    }

    private fun rvSetup() {
        binding.rvExploreImages.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvExploreImages.setHasFixedSize(true)
        binding.rvExploreImages.adapter = adapter
    }
}