package com.simplesurance.dogbreed.data.local

import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.model.DogBreedImages
import kotlinx.coroutines.flow.StateFlow

interface LocalDataSource {
    suspend fun getDogBreeds(): List<DogBreed>
    suspend fun getDogBreedImages(breedName: String): Resource<List<String>>
    suspend fun storeDogBreedListInDb(dogBreeds: List<DogBreed>)
    suspend fun storeDogBreedImageListInDb(breedImages: DogBreedImages)
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)
    suspend fun getFavouriteDogBreeds(): List<DogBreed>
}