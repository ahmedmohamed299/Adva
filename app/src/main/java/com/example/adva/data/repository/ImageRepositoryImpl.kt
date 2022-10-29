package com.example.adva.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.adva.data.model.ImageItem
import com.example.adva.data.repository.dataSource.ImageLocalDataSource
import com.example.adva.data.repository.dataSource.ImageRemoteDataSource
import com.example.adva.data.repository.dataSourceImpl.ImagesPagingDataSource
import com.example.adva.data.utils.Resource
import com.example.adva.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import retrofit2.Response

class ImageRepositoryImpl(
    private val imageLocalDataSource: ImageLocalDataSource,
    private val imageRemoteDataSource: ImageRemoteDataSource
) : ImageRepository {
    override suspend fun getAllImages(): Resource<Flow<PagingData<ImageItem>>> {
        return getImagesFromDb()
    }

    private suspend fun responseToResource(response: Response<List<ImageItem>>): Resource<Flow<PagingData<ImageItem>>> {

        if (response.isSuccessful) {
            response.body()?.let {imageItemList ->
                imageItemList.let { imageItem ->
                    Log.d("responseToResource", "responseToResource: $imageItemList")
                    imageLocalDataSource.saveImagesToDb(imageItem)

                }
                return getImagesFromDb()
            }
        }

        return Resource.Error(response.message())
    }


    private suspend fun getImagesFromDb():Resource<Flow<PagingData<ImageItem>>> {


        return if (imageLocalDataSource.getAllImagesFromDb().isEmpty()){
            responseToResource(imageRemoteDataSource.getImagesRemote())

        }else{
            val images = Pager(
                PagingConfig(
                    pageSize = 10,
                    initialLoadSize =10
                ), pagingSourceFactory =  {ImagesPagingDataSource(imageLocalDataSource)}

            ) .flow
//            Log.d("getImagesFromDb", "getImagesFromDb:${images.count()} ")
            Resource.Success(images)
        }



    }
}