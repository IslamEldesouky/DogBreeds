package com.simplesurance.dogbreed.domain.usecase.dogBreeds

import com.simplesurance.dogbreed.domain.model.DogBreed

interface UseCase {
    suspend fun getDogBreeds(): List<DogBreed>?
}