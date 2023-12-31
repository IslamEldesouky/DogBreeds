package com.simplesurance.dogbreed.data.local

import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.model.DogBreedImages

interface LocalDataSource {
    suspend fun getDogBreeds(): List<DogBreed>
    suspend fun getDogBreedImages(breedName: String): List<String>
    suspend fun storeDogBreedListInDb(dogBreeds: List<DogBreed>?)
    suspend fun storeDogBreedImageListInDb(breedImages: DogBreedImages)
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)
    suspend fun getFavouriteDogBreeds(): List<DogBreed>
}