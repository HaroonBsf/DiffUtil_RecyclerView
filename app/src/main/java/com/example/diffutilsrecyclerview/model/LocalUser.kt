package com.example.diffutilsrecyclerview.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.diffutilsrecyclerview.data.Converters

@Entity(tableName = "user_table")
data class LocalUser(
    @PrimaryKey val id: Int,
    val firstName: String,
    val maidenName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val phone: String,
    val username: String,
    val birthDate: String,
    val bloodGroup: String,
    val eyeColor: String,
    val email: String,
    val image: String,
    val hair: Hair,
    val address: Address
)