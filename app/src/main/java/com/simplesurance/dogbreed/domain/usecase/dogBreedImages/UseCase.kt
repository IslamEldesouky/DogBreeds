package com.simplesurance.dogbreed.domain.usecase.dogBreedImages

import com.simplesurance.dogbreed.data.Resource

interface UseCase {
    suspend fun getDogBreedImages(breed : String): List<String>
}