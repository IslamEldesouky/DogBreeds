package com.simplesurance.dogbreed.data.remote

import android.util.Log
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.data.local.db.Constants.COMMA
import com.simplesurance.dogbreed.data.remote.dto.DogBreedResponse
import com.simplesurance.dogbreed.data.remote.dto.DogBreedWithSubBreed
import com.simplesurance.dogbreed.data.remote.service.DogBreedService
import com.simplesurance.dogbreed.domain.model.DogBreed

class RemoteDataSourceImpl(private val dogBreedService: DogBreedService) : RemoteDataSource {

    override suspend fun getDogBreeds(): Resource<List<DogBreed>> {
        try {
            val dogBreedList = mutableListOf<DogBreed>()
            val res = dogBreedService.fetchDogBreeds()
            val dogBreedNameWithSubBreedList = mutableListOf<DogBreedWithSubBreed>()

            when (res.isSuccessful) {
                true -> {
                    res.body()?.let { body ->
                        if (body.status == DogBreedResponse.SUCCESS_STATUS) {
                            body.message.entries.forEach {
                                dogBreedNameWithSubBreedList.add(
                                    DogBreedWithSubBreed(
                                        it.key,
                                        it.value.joinToString(COMMA) //Joining all the subbreeds by comma
                                    )
                                )
                            }
                            return Resource.Success(data = dogBreedList)
                        } else return Resource.DataError(errorCode = body.code)
                    } ?: return Resource.DataError(errorCode = res.code())
                }

                false -> return Resource.DataError(errorCode = res.code())
            }
        } catch (e: Exception) {
            Log.e("NETWORK_API_ERROR", "List cannot load ${e.hashCode()}")
            return Resource.DataError(errorCode = e.hashCode())
        }
    }

    override suspend fun getDogBreedImages(breedName: String): Resource<List<String>> {
        try {
            val res = dogBreedService.fetchDogBreedImages(breedName)

            when (res.isSuccessful) {
                true -> {
                    res.body()?.let { body ->
                        if (body.status == DogBreedResponse.SUCCESS_STATUS) {
                            return Resource.Success(data = body.message)
                        } else return Resource.DataError(errorCode = body.code)
                    } ?: return Resource.DataError(errorCode = res.code())
                }

                false -> return Resource.DataError(errorCode = res.code())
            }
        } catch (e: Exception) {
            Log.e("NETWORK_API_ERROR", "Cannot get dog breed images ${e.hashCode()}")
            return Resource.DataError(errorCode = e.hashCode())
        }
    }
}