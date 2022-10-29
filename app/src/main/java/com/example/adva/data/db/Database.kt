package com.example.adva.data.db

import androidx.room.RoomDatabase
import com.example.adva.data.model.ImageItem

@androidx.room.Database(
    entities = [
        ImageItem::class

    ], version = 1, exportSchema = false
)

abstract class Database: RoomDatabase() {
    abstract fun getImages(): ImageDao
}