package com.simplesurance.dogbreed.data.remote.service

import com.simplesurance.dogbreed.data.remote.api.Endpoints.GET_BREEDS
import com.simplesurance.dogbreed.data.remote.api.Endpoints.GET_BREEDS_IMAGES
import com.simplesurance.dogbreed.data.remote.dto.DogBreedImageResponse
import com.simplesurance.dogbreed.data.remote.dto.DogBreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedService {
    @GET(GET_BREEDS)
    suspend fun fetchDogBreeds(): Response<DogBreedResponse>

    @GET(GET_BREEDS_IMAGES)
    suspend fun fetchDogBreedImages(@Path("breed_name") breedName: String): Response<DogBreedImageResponse>
}