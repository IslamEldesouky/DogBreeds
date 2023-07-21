package com.simplesurance.dogbreed.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.model.DogBreedImages

@Database(version = 1, entities = [DogBreed::class, DogBreedImages::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class DogBreedDatabase : RoomDatabase() {

    abstract fun dogBreedsDao(): DogBreedDao
}