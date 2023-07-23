package com.simplesurance.dogbreed.domain.usecase.dogBreedImages

interface DogBreedImagesUseCase {
    suspend fun getDogBreedImages(breed : String): List<String>
}