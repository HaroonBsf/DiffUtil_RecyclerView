package com.example.diffutilsrecyclerview.data.models

data class Address(
    val address: String,
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postalCode: String,
    val state: String,
    val stateCode: String
)

data class Bank(
    val cardExpire: String,
    val cardNumber: String,
    val cardType: String,
    val currency: String,
    val iban: String
)

data class Company(
    val address: Address,
    val department: String,
    val name: String,
    val title: String
)

data class Coordinates(
    val lat: Double,
    val lng: Double
)

data class Crypto(
    val coin: String,
    val network: String,
    val wallet: String
)

data class Hair(
    val color: String,
    val type: String
)

data class JsonResponse(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)

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