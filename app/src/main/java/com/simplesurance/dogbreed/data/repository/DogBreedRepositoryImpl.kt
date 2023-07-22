package com.simplesurance.dogbreed.data.repository

import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.data.local.LocalDataSource
import com.simplesurance.dogbreed.data.remote.RemoteDataSource
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.repository.DogBreedRepository

class DogBreedRepositoryImpl(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : DogBreedRepository {
    override suspend fun getDogBreeds(): List<DogBreed>? {
       val list = localDataSource.getDogBreeds()
        if(list.isEmpty()){
           return remoteDataSource.getDogBreeds().let {
                localDataSource.storeDogBreedListInDb(it)
               it
            }
        }
        return list
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
}