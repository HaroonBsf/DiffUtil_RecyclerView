package com.example.diffutilsrecyclerview.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import retrofit2.HttpException
import java.io.IOException

class UnsplashPagingSource(val apiServiceThree: ApiService, private val clientId: String) :
    PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        return try {
            val page = params.key ?: 1
            val response = apiServiceThree.getUnsplashImages(clientId, page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
