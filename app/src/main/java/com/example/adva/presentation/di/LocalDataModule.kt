package com.example.adva.presentation.di

import com.example.adva.data.db.ImageDao
import com.example.adva.data.repository.dataSource.ImageLocalDataSource
import com.example.adva.data.repository.dataSourceImpl.ImageLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideImageLocalDataSource(imageDao: ImageDao): ImageLocalDataSource {
        return ImageLocalDataSourceImpl(imageDao)
    }
}