package com.example.adva.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.adva.data.db.Database
import com.example.adva.data.db.ImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideNewsDatabase(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, "adve_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideImagesDao(database: Database): ImageDao {
        return database.getImages()
    }
}