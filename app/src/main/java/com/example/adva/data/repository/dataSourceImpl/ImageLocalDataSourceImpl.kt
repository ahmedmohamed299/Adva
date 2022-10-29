package com.example.adva.data.repository.dataSourceImpl


import com.example.adva.data.db.ImageDao
import com.example.adva.data.model.ImageItem
import com.example.adva.data.repository.dataSource.ImageLocalDataSource


class ImageLocalDataSourceImpl(private val imageDao: ImageDao) : ImageLocalDataSource {
    override suspend fun getImagesFromDb(limit: Int, offset: Int): List<ImageItem> {
        return imageDao.getImagesList(limit,offset)
    }

    override suspend fun getAllImagesFromDb(): List<ImageItem> {
        return imageDao.getAllImages()
    }

    override suspend fun saveImagesToDb(images: List<ImageItem>) {
        imageDao.insert(images)
    }


}