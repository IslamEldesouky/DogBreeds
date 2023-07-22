package com.simplesurance.dogbreed.data.remote

import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.domain.model.DogBreed

interface RemoteDataSource {
    suspend fun getDogBreeds(): List<DogBreed>
    suspend fun getDogBreedImages(breedName: String): Resource<List<String>>
}