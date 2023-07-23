package com.simplesurance.dogbreed.domain.usecase.dogBreeds

import com.simplesurance.dogbreed.domain.model.DogBreed

interface DogBreedUseCase {
    suspend fun getDogBreeds(): List<DogBreed>?
}