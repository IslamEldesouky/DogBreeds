package com.simplesurance.dogbreed.domain.usecase.dogBreeds

import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.repository.DogBreedRepository

class DogBreedUseCase(private val repository: DogBreedRepository) : UseCase {

    override suspend fun getDogBreeds(): List<DogBreed>? {
        return repository.getDogBreeds()
    }
}