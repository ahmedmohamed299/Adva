package com.example.adva.data.repository.dataSourceImpl

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.adva.data.model.ImageItem
import com.example.adva.data.repository.dataSource.ImageLocalDataSource
import kotlinx.coroutines.delay

private const val TAG = "IMAGE_PAGING_ERROR"

class ImagesPagingDataSource(private val images: ImageLocalDataSource) : PagingSource<Int, ImageItem>() {

    /*
    it suppose to use Remote Mediator but this data is to small so
    best case for this Scenario to save all data to database and call it in
    paging source
     */

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageItem> {
        val pageNumber = params.key ?: 0
        val imagesList=images.getImagesFromDb(params.loadSize, pageNumber * params.loadSize)
        return try {
            if (pageNumber!=0) delay(2000) // simulate page loading
            LoadResult.Page(
                data = imagesList,
                prevKey = if (pageNumber==0) null else pageNumber-1,
                nextKey = if (imagesList.isEmpty()) null else pageNumber + 1
            )


        }catch (e:Exception){
            Log.e(TAG, "load: ${e.message}" )
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}