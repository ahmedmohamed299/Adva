package com.example.adva.presentation.di

import com.example.adva.domain.repository.ImageRepository
import com.example.adva.domain.useCase.GetImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetImagesUseCase(imageRepository: ImageRepository): GetImagesUseCase{
        return GetImagesUseCase(imageRepository)
    }
}