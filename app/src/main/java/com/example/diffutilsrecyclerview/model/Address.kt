package com.example.diffutilsrecyclerview.model

data class Address(
    val address: String,
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postalCode: String,
    val state: String,
    val stateCode: String
)