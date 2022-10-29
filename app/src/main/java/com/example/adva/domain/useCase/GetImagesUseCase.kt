package com.example.adva.domain.useCase

import com.example.adva.domain.repository.ImageRepository

class GetImagesUseCase(private val imageRepository: ImageRepository) {

    suspend fun execute() = imageRepository.getAllImages()
}