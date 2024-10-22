package com.example.diffutilsrecyclerview.data

import androidx.room.TypeConverter
import com.example.diffutilsrecyclerview.model.Address
import com.example.diffutilsrecyclerview.model.Hair
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromHair(hair: Hair): String {
        val gson = Gson()
        return gson.toJson(hair)
    }

    @TypeConverter
    fun toHair(hairString: String): Hair {
        val gson = Gson()
        val type = object : TypeToken<Hair>() {}.type
        return gson.fromJson(hairString, type)
    }

    @TypeConverter
    fun fromAddress(address: Address): String {
        val gson = Gson()
        return gson.toJson(address)
    }

    @TypeConverter
    fun toAddress(addressString: String): Address {
        val gson = Gson()
        val type = object : TypeToken<Address>() {}.type
        return gson.fromJson(addressString, type)
    }
}
