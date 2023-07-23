package com.simplesurance.dogbreed.domain.usecase.dogBreedImages

import com.simplesurance.dogbreed.domain.repository.DogBreedRepository

class DogBreedImagesUseCaseImpl(private val repository: DogBreedRepository) : DogBreedImagesUseCase {

    override suspend fun getDogBreedImages(breed: String): List<String> {
        return repository.getDogBreedImages(breed)
    }
}