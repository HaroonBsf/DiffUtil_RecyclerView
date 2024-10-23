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

fun List<LocalUser>.users(): List<User>{
    return this.map { localUser ->
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
}