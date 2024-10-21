package com.example.diffutilsrecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.model.JsonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = Adapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchUser()

    }

    private fun fetchUser() {
        val apiService = RetrofitClient.apiService
        apiService.getData().enqueue(object : Callback<JsonResponse?> {
            override fun onResponse(p0: Call<JsonResponse?>, response: Response<JsonResponse?>) {
                if (response.isSuccessful){
                    val userList = response.body()?.users ?: emptyList()
                    adapter.updateUsers(userList)
                } else{
                    Toast.makeText(this@MainActivity, "Response Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<JsonResponse?>, p1: Throwable) {

            }
        })

    }
}