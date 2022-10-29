package com.example.adva.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.adva.data.model.ImageItem

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageItem: List<ImageItem>)


    @Query("SELECT * FROM images ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getImagesList(limit: Int, offset: Int): List<ImageItem>

    @Query("SELECT * FROM images ")
    fun getAllImages(): List<ImageItem>


}