package com.example.diffutilsrecyclerview.di

import android.content.Context
import androidx.room.Room
import com.example.diffutilsrecyclerview.common.AppDatabase
import com.example.diffutilsrecyclerview.data.database.UserDao
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "user_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideDataRepository(apiService: ApiService, userDao: UserDao): DataRepository {
        return DataRepository(apiService, userDao)
    }

    /*@Provides
    @Singleton
    fun provideDataRepository(apiService: ApiService, appDatabase: AppDatabase): DataRepository {
        return DataRepository(apiService, appDatabase)
    }*/
}