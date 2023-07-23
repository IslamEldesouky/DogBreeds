package com.simplesurance.dogbreed.util

import com.simplesurance.dogbreed.data.local.db.Constants.COMMA
import com.simplesurance.dogbreed.data.remote.dto.DogBreedWithSubBreed

fun getSubBreedsCount(subBreeds : String) : String{
    var count = "0"
    if (subBreeds.isNotEmpty() && subBreeds.contains(COMMA)) {
        count = subBreeds.split(COMMA).size.toString()
    } else if (subBreeds.isNotEmpty() && !subBreeds.contains(COMMA)) {
        count = "1"
    }
    return "Has $count Sub Breeds"
}

fun getSubBreedsCountV2(subBreeds: String): String {
    var count = "0"
    if (subBreeds.isNotEmpty() && subBreeds.contains(COMMA)) {
        count = subBreeds.split(COMMA).size.toString()
    } else if (subBreeds.isNotEmpty() && !subBreeds.contains(COMMA)) {
        count = "1"
    }
    return count
}