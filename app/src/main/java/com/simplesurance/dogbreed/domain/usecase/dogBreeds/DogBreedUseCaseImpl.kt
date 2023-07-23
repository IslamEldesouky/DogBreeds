package com.simplesurance.dogbreed.domain.usecase.dogBreeds

import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.repository.DogBreedRepository

class DogBreedUseCaseImpl(private val repository: DogBreedRepository) : DogBreedUseCase {

    override suspend fun getDogBreeds(): List<DogBreed>? {
        return repository.getDogBreeds()
    }
}