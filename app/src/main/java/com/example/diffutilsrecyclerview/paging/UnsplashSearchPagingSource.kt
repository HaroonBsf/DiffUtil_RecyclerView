package com.example.diffutilsrecyclerview.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import retrofit2.HttpException
import java.io.IOException

class UnsplashSearchPagingSource(
    private val apiService: ApiService,
    private val clientId: String,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getUnsplashSearchImages(clientId, query)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
