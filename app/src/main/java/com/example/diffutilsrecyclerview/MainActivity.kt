package com.example.diffutilsrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.data.AppDatabase
import com.example.diffutilsrecyclerview.data.DataRepository
import com.example.diffutilsrecyclerview.data.UserViewModel
import com.example.diffutilsrecyclerview.data.UserViewModelFactory
import com.example.diffutilsrecyclerview.databinding.ActivityMainBinding
import com.example.diffutilsrecyclerview.model.Address
import com.example.diffutilsrecyclerview.model.Coordinates
import com.example.diffutilsrecyclerview.model.Hair
import com.example.diffutilsrecyclerview.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(
            DataRepository(
                RetrofitClient.getData(), database.userDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = Adapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getUserData()
        viewModel.userData.observe(this, Observer { response ->
            response?.let {
                adapter.updateUsers(it.users)
                binding.recyclerView.adapter = adapter
            }
        })

        observeLocalUsers()
    }

    private fun observeLocalUsers() {
        viewModel.localUserData.observe(this, Observer { localUsers ->
            localUsers?.let {
                val userList = localUsers.map { localUser ->
                    User(
                        id = localUser.id,
                        firstName = localUser.firstName,
                        lastName = localUser.lastName,
                        email = localUser.email,
                        image = localUser.image,
                        phone = localUser.phone,
                        age = localUser.age,
                        birthDate = localUser.birthDate,
                        bloodGroup = localUser.bloodGroup,
                        ein = "",
                        eyeColor = localUser.eyeColor,
                        gender = localUser.gender,
                        hair = Hair(
                            color = localUser.hair.color,
                            type = localUser.hair.type
                        ),
                        address = Address(
                            localUser.address.address,
                            localUser.address.city,
                            Coordinates(0.0, 0.0),
                            localUser.address.country,
                            "",
                            localUser.address.state,
                            ""
                        ),
                        height = 0.0,
                        ip = "",
                        macAddress = "",
                        maidenName = localUser.maidenName,
                        password = "",
                        role = "",
                        ssn = "",
                        university = "",
                        userAgent = "",
                        username = localUser.username
                    )
                }
                adapter.updateUsers(userList)
                binding.recyclerView.adapter = adapter
            }
        })
    }
}