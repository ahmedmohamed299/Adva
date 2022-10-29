package com.example.adva.domain.repository

import androidx.paging.PagingData
import com.example.adva.data.model.ImageItem
import com.example.adva.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getAllImages():Resource<Flow<PagingData<ImageItem>>>
}