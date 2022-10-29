package com.example.adva.presentation.di

import com.example.adva.data.repository.ImageRepositoryImpl
import com.example.adva.data.repository.dataSource.ImageLocalDataSource
import com.example.adva.data.repository.dataSource.ImageRemoteDataSource
import com.example.adva.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideImageRepository(
        imageLocalDataSource: ImageLocalDataSource,
        imageRemoteDataSource: ImageRemoteDataSource
    ): ImageRepository {
        return ImageRepositoryImpl(imageLocalDataSource, imageRemoteDataSource)
    }
}