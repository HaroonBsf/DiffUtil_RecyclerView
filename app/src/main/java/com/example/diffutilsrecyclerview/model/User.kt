package com.example.diffutilsrecyclerview.model

data class User(
    val address: Address,
    val age: Int,
    val birthDate: String,
    val bloodGroup: String,
    val ein: String,
    val email: String,
    val eyeColor: String,
    val firstName: String,
    val gender: String,
    val hair: Hair,
    val height: Double,
    val id: Int,
    val image: String,
    val ip: String,
    val lastName: String,
    val macAddress: String,
    val maidenName: String,
    val password: String,
    val phone: String,
    val role: String,
    val ssn: String,
    val university: String,
    val userAgent: String,
    val username: String,
)

fun List<User>.localUsers(): List<LocalUser> {
    return this.map { user ->
        LocalUser(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            maidenName = user.maidenName,
            age = user.age,
            gender = user.gender,
            phone = user.phone,
            username = user.username,
            birthDate = user.birthDate,
            bloodGroup = user.bloodGroup,
            eyeColor = user.eyeColor,
            email = user.email,
            image = user.image,
            hair = Hair(user.hair.color, user.hair.type),
            address = Address(
                user.address.address,
                user.address.city,
                Coordinates(0.0, 0.0),
                user.address.country,
                "",
                user.address.state,
                ""
            )
        )
    }
}