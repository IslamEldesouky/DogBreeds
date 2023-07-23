package com.simplesurance.dogbreed.domain.usecase.dogBreedImages

import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.domain.repository.DogBreedRepository

class DogBreedImagesUseCase(private val repository: DogBreedRepository) : UseCase {

    override suspend fun getDogBreedImages(breed: String): List<String> {
        return repository.getDogBreedImages(breed)
    }
}