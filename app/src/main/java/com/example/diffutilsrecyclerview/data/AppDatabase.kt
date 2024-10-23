package com.example.diffutilsrecyclerview.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.diffutilsrecyclerview.model.Address
import com.example.diffutilsrecyclerview.model.Hair
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.diffutilsrecyclerview.model.LocalUser

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
    fun fromHair(hair: Hair): String = Gson().toJson(hair)

    @TypeConverter
    fun toHair(hairString: String): Hair = Gson().fromJson(hairString, Hair::class.java)

    @TypeConverter
    fun fromAddress(address: Address): String = Gson().toJson(address)

    @TypeConverter
    fun toAddress(addressString: String): Address = Gson().fromJson(addressString, Address::class.java)
}