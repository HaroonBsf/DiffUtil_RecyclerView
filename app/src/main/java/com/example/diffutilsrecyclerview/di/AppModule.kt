package com.example.diffutilsrecyclerview.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.diffutilsrecyclerview.common.AppDatabase
import com.example.diffutilsrecyclerview.data.database.UserDao
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.data.database.RecipeDao
import com.example.diffutilsrecyclerview.data.database.UnsplashDao
import com.example.diffutilsrecyclerview.data.database.UnsplashRemoteKeysDao
import com.example.diffutilsrecyclerview.repository.DataRepository
import com.example.diffutilsrecyclerview.ui.fragment.RecipesFragment
import com.example.diffutilsrecyclerview.ui.fragment.UsersFragment
import com.example.diffutilsrecyclerview.util.API_HOST_CATEGORIES
import com.example.diffutilsrecyclerview.util.API_KEY_CATEGORIES
import com.example.diffutilsrecyclerview.util.BASE_URL
import com.example.diffutilsrecyclerview.util.BASE_URL_CATEGORIES
import com.example.diffutilsrecyclerview.util.BASE_URL_UNSPLASH
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiThree
    fun provideRetrofitUnsplash(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL_UNSPLASH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @ApiThree
    fun provideApiServiceThree(@ApiThree retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @ApiTwo
    fun provideApiKey(): OkHttpClient{
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("x-rapidapi-key", API_KEY_CATEGORIES)
                .header("x-rapidapi-host", API_HOST_CATEGORIES)
                .build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    @ApiTwo
    fun provideRetrofitTwo(@ApiTwo okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL_CATEGORIES)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @ApiTwo
    fun provideApiServiceTwo(@ApiTwo retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @ApiOne
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @ApiOne
    fun provideApiService(@ApiOne retrofit: Retrofit): ApiService {
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
    fun provideRecipeDao(database: AppDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    fun provideUnsplashDao(database: AppDatabase): UnsplashDao {
        return database.unsplashDao()
    }

    @Provides
    fun provideUnsplashRemoteKeysDao(database: AppDatabase): UnsplashRemoteKeysDao {
        return database.unsplashRemoteKeysDao()
    }

    @Provides
    @Singleton
    @ExperimentalPagingApi
    fun provideDataRepository(
        @ApiOne apiServiceOne: ApiService,
        @ApiTwo apiServiceTwo: ApiService,
        @ApiThree apiServiceThree: ApiService,
        appDatabase: AppDatabase): DataRepository {
        return DataRepository(apiServiceOne, apiServiceTwo, apiServiceThree, appDatabase)
    }

    @Provides
    @ExperimentalPagingApi
    fun provideRecipesFragment(): RecipesFragment {
        return RecipesFragment()
    }

    @Provides
    @ExperimentalPagingApi
    fun provideUsersFragment(): UsersFragment {
        return UsersFragment()
    }
}