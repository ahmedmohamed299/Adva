package com.example.adva.data.repository.dataSource

import com.example.adva.data.model.ImageItem
import retrofit2.Response

interface ImageRemoteDataSource {

    suspend fun getImagesRemote():Response<List<ImageItem>>
}