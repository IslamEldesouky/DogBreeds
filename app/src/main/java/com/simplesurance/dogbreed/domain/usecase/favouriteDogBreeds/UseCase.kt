package com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds

import com.simplesurance.dogbreed.domain.model.DogBreed

interface UseCase {
    suspend fun getFavouriteDogBreeds(): List<DogBreed>
    suspend fun addToFavourites(name: String, isFavourite: Boolean)
}