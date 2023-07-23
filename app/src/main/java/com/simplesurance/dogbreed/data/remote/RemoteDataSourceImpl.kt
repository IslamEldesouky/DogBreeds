package com.simplesurance.dogbreed.data.remote

import android.util.Log
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.data.local.db.Constants.COMMA
import com.simplesurance.dogbreed.data.remote.dto.DogBreedResponse
import com.simplesurance.dogbreed.data.remote.dto.DogBreedWithSubBreed
import com.simplesurance.dogbreed.data.remote.service.DogBreedService
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.util.extensions.asyncAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(private val dogBreedService: DogBreedService) : RemoteDataSource {

    override suspend fun getDogBreeds(): List<DogBreed> {
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
                            var iterator = 0
                            withContext(Dispatchers.IO) {
                                this.asyncAll(dogBreedNameWithSubBreedList){res}.awaitAll().forEach {
                                    it.body()?.let { breed ->
                                        dogBreedList.add(
                                            DogBreed(
                                                dogBreedNameWithSubBreedList[iterator].name,
                                                dogBreedNameWithSubBreedList[iterator].subBreeds,
                                                false
                                            )
                                        )
                                    }
                                    iterator++
                                }
                            }
                            Log.d("RESULT_REMOTE_DATA_SOURCE", dogBreedList.size.toString())
                            return dogBreedList
                        } else return emptyList()
                    } ?: return emptyList()
                }

                false -> return emptyList()
            }
        } catch (e: Exception) {
            Log.e("NETWORK_API_ERROR", "List cannot load ${e.hashCode()}")
            return emptyList()
        }
    }

    override suspend fun getDogBreedImages(breedName: String): List<String> {
        try {
            val res = dogBreedService.fetchDogBreedImages(breedName)

            when (res.isSuccessful) {
                true -> {
                    res.body()?.let { body ->
                        if (body.status == DogBreedResponse.SUCCESS_STATUS) {
                            return body.message
                        } else return emptyList()
                    } ?: return emptyList()
                }

                false -> return emptyList()
            }
        } catch (e: Exception) {
            Log.e("NETWORK_API_ERROR", "Cannot get dog breed images ${e.hashCode()}")
            return emptyList()
        }
    }

//    private suspend fun prepareDogsBreedListWithImage(
//        scope: CoroutineScope,
//        dogBreedNameWithSubBreedList: List<DogBreedWithSubBreed>,
//        dogBreedList: MutableList<DogBreed>,
//    ) {
//        var iterator = 0
//        scope.asyncAll(dogBreedNameWithSubBreedList) { dogBreedService.fetchDogBreeds() }
//            .awaitAll() //Awaits for completion of given deferred values without blocking a thread and
//            // resumes normally with the list of values when all deferred computations are complete.
//            .forEach { breedImageResponse ->
//                breedImageResponse.body()?.let { breedImage ->
//                    dogBreedList.add(
//                        DogBreed(
//                            dogBreedNameWithSubBreedList[iterator].name,
//                            dogBreedNameWithSubBreedList[iterator].subBreeds,
//                            false
//                        )
//                    )
//                }
//                iterator++
//            }
//    }
}