package com.example.adva.data.repository.dataSourceImpl


import com.example.adva.data.apis.APIsService
import com.example.adva.data.model.ImageItem
import com.example.adva.data.repository.dataSource.ImageRemoteDataSource
import retrofit2.Response

class ImageRemoteDataSourceImpl(private val apIsService: APIsService):ImageRemoteDataSource {
    override suspend fun getImagesRemote(): Response<List<ImageItem>> {
        return apIsService.getImages()
    }
}