package com.simplesurance.dogbreed.data.remote.dto

data class DogBreedImageResponse(
    val status: String,
    val message: List<String>,
    val code: Int
)