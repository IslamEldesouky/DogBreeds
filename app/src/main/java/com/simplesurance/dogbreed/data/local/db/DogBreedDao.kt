package com.simplesurance.dogbreed.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.model.DogBreedImages

@Dao
interface DogBreedDao {
    @Query("SELECT * FROM dogbreed ORDER BY name")
    suspend fun getDogBreeds(): List<DogBreed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreed(dog: DogBreed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDogBreeds(dogBreeds: List<DogBreed>?)

    @Query("SELECT image_urls FROM dogbreedimages WHERE breed_name =:breedName")
    suspend fun getDogBreedImages(breedName: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreedImages(dogBreeds: DogBreedImages)

    @Query("UPDATE dogbreed SET is_favourite = :isFavourite WHERE name LIKE :name")
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)

    @Query("SELECT * FROM dogbreed WHERE is_favourite = 1")
    suspend fun getFavouriteDogBreeds(): List<DogBreed>

    @Query("SELECT * FROM dogbreed WHERE name LIKE :name")
    fun getOneDogBreed(name: String): DogBreed
}