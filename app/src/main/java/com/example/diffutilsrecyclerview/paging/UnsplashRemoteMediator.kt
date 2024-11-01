package com.example.diffutilsrecyclerview.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.common.AppDatabase
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashRemoteKeys
import com.example.diffutilsrecyclerview.di.ApiThree
import com.example.diffutilsrecyclerview.util.API_KEY_UNSPLASH

@ExperimentalPagingApi
class UnsplashRemoteMediator(
    @ApiThree private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, UnsplashPhoto>() {

    private val unsplashDao = appDatabase.unsplashDao()
    private val unsplashRemoteKeysDao = appDatabase.unsplashRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashPhoto>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }

            val response = apiService.getUnsplashImages(API_KEY_UNSPLASH, currentPage, state.config.pageSize)
            val endOfPaginationReached = response.isEmpty()

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    unsplashDao.deleteImages()
                    unsplashRemoteKeysDao.deleteRemoteKeys()
                }

                unsplashDao.insertImages(response)
                val keys = response.map { unsplash ->
                    UnsplashRemoteKeys(
                        id = unsplash.id,
                        prevPage = if (currentPage == 1) null else currentPage - 1,
                        nextPage = if (endOfPaginationReached) null else currentPage + 1
                    )
                }
                unsplashRemoteKeysDao.insertRemoteKeys(keys)
            }
            MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UnsplashPhoto>): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UnsplashPhoto>): UnsplashRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplash -> unsplashRemoteKeysDao.getRemoteKeys(id = unsplash.id) }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UnsplashPhoto>): UnsplashRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplash -> unsplashRemoteKeysDao.getRemoteKeys(id = unsplash.id) }
    }
}
