package com.simplesurance.dogbreed.data.local

import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.data.local.db.Converters
import com.simplesurance.dogbreed.data.local.db.DogBreedDao
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.model.DogBreedImages

class LocalDataSourceImpl(private val dao: DogBreedDao) : LocalDataSource {
    override suspend fun getDogBreeds(): List<DogBreed> {
        return dao.getDogBreeds()
    }

    override suspend fun storeDogBreedListInDb(dogBreeds: List<DogBreed>?) {
        dao.insertAllDogBreeds(dogBreeds)
    }

    override suspend fun getDogBreedImages(breedName: String): Resource<List<String>> {
        val dogBreedImageList = dao.getDogBreedImages(breedName)
        return if (dogBreedImageList.isEmpty()) {
            Resource.empty()
        } else {
            val converter = Converters()
            Resource.Success(converter.fromString(dogBreedImageList[0]))
        }
    }

    override suspend fun storeDogBreedImageListInDb(breedImages: DogBreedImages) {
        dao.insertDogBreedImages(breedImages)
    }

    override suspend fun updateDogBreeds(name: String, isFavourite: Boolean) {
        dao.updateDogBreeds(name, isFavourite)
    }

    override suspend fun getFavouriteDogBreeds(): List<DogBreed> {
        return dao.getFavouriteDogBreeds()
    }
}