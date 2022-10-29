package com.example.adva.data.apis

import com.example.adva.data.model.ImageItem
import retrofit2.Response
import retrofit2.http.GET

interface APIsService {

    @GET("photos")
    suspend fun getImages(): Response<List<ImageItem>>
}