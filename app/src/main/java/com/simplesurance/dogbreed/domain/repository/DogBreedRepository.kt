package com.simplesurance.dogbreed.domain.repository

import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.domain.model.DogBreed

interface DogBreedRepository {
    suspend fun getDogBreeds(): List<DogBreed>?
    suspend fun getDogBreedImages(breedName: String): List<String>
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)
    suspend fun getFavouriteDogBreeds(): List<DogBreed>
}