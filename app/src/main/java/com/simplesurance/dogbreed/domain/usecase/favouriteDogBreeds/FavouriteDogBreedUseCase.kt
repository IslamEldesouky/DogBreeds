package com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds

import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.repository.DogBreedRepository

class FavouriteDogBreedUseCase(private val repository: DogBreedRepository) : UseCase {
    override suspend fun getFavouriteDogBreeds(): List<DogBreed> {
        return repository.getFavouriteDogBreeds()
    }

    override suspend fun addToFavourites(name: String, isFavourite: Boolean) {
        return repository.updateDogBreeds(name, isFavourite)
    }
}