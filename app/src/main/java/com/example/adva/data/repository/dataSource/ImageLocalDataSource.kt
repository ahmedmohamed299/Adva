package com.example.adva.data.repository.dataSource

import com.example.adva.data.model.ImageItem

interface ImageLocalDataSource {
    suspend fun getImagesFromDb(limit: Int, offset: Int):List<ImageItem>
    suspend fun getAllImagesFromDb():List<ImageItem>
    suspend fun saveImagesToDb(images:List<ImageItem>)

}