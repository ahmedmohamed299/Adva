package com.example.adva.presentation.di

import com.example.adva.data.apis.APIsService
import com.example.adva.data.repository.dataSource.ImageRemoteDataSource
import com.example.adva.data.repository.dataSourceImpl.ImageRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideImageRemoteDataSource(apIsService: APIsService): ImageRemoteDataSource {
        return ImageRemoteDataSourceImpl(apIsService)
    }
}