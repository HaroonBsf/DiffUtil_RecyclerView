package com.example.diffutilsrecyclerview.common

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.diffutilsrecyclerview.data.database.RecipeDao
import com.example.diffutilsrecyclerview.data.database.UnsplashDao
import com.example.diffutilsrecyclerview.data.database.UnsplashRemoteKeysDao
import com.example.diffutilsrecyclerview.data.database.UserDao
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.Address
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.Hair
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalUser
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhotoUrls
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashRemoteKeys

@Database(
    entities = [LocalUser::class,
        LocalRecipeModel::class,
        UnsplashPhoto::class, UnsplashRemoteKeys::class],
    version = 6, exportSchema = false
)
@TypeConverters(
    UserConverters::class,
    RecipeConverter::class,
    UnsplashImagesConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun recipeDao(): RecipeDao
    abstract fun unsplashDao(): UnsplashDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}

class UserConverters {
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

class RecipeConverter {
    @TypeConverter
    fun fromIngredients(ingredients: List<String>): String {
        return Gson().toJson(ingredients)
    }

    @TypeConverter
    fun toIngredients(ingredientsString: String): List<String> {
        return Gson().fromJson(ingredientsString, object : TypeToken<List<String>>() {}.type)
    }
}

class UnsplashImagesConverters {
    @TypeConverter
    fun fromUrl(url: UnsplashPhotoUrls): String {
        val gson = Gson()
        return gson.toJson(url)
    }

    @TypeConverter
    fun toUrl(urlString: String): UnsplashPhotoUrls {
        val gson = Gson()
        val type = object : TypeToken<UnsplashPhotoUrls>() {}.type
        return gson.fromJson(urlString, type)
    }
}