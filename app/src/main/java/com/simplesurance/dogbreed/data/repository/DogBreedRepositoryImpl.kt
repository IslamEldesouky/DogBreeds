package com.simplesurance.dogbreed.data.repository

import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.data.local.LocalDataSource
import com.simplesurance.dogbreed.data.remote.RemoteDataSource
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.repository.DogBreedRepository

class DogBreedRepositoryImpl(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : DogBreedRepository {
    override suspend fun getDogBreeds(): List<DogBreed> {
        return getDogBreedFromDB()
    }

    override suspend fun getDogBreedImages(breedName: String): Resource<List<String>> {
        return remoteDataSource.getDogBreedImages(breedName)
    }

    override suspend fun updateDogBreeds(name: String, isFavourite: Boolean) {
        localDataSource.updateDogBreeds(name, isFavourite)
    }

    override suspend fun getFavouriteDogBreeds(): List<DogBreed> {
        return localDataSource.getFavouriteDogBreeds()
    }

    private suspend fun getDogBreedFromDB(): List<DogBreed> {
        var list = localDataSource.getDogBreeds()
        if (list.isEmpty()) {
            list = getDogBreedFromAPI()
            localDataSource.storeDogBreedListInDb(list)
        }
        return list
    }

    private suspend fun getDogBreedFromAPI(): List<DogBreed> {
        val list = remoteDataSource.getDogBreeds().data
        return list!!
    }
}