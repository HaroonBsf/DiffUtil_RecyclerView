package com.example.diffutilsrecyclerview.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.diffutilsrecyclerview.data.models.Address
import com.example.diffutilsrecyclerview.data.models.Hair
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.diffutilsrecyclerview.data.models.LocalUser

// Singleton Pattern
@Database(entities = [LocalUser::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

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