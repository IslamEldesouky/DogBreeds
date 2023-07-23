package com.simplesurance.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.simplesurance.dogbreed.data.local.db.DogBreedDao
import com.simplesurance.dogbreed.data.local.db.DogBreedDatabase
import com.simplesurance.dogbreed.domain.model.DogBreed
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class DogBreedDbTest {

    private lateinit var db: DogBreedDatabase
    private lateinit var dogBreedDao: DogBreedDao

    @Before
    fun setupDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DogBreedDatabase::class.java
        ).allowMainThreadQueries().build()

        dogBreedDao  = db.dogBreedsDao()
    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun insertDogBreedSuccess() = runBlocking {
        val akita = DogBreed(name = "Akita", subBreeds = "", isFavourite = false)
        dogBreedDao.insertDogBreed(akita)

        val job = async {
           assert (dogBreedDao.getDogBreeds().contains(akita))
        }
        job.cancelAndJoin()
    }

    @Test
    fun updateDogBreedSuccess() = runBlocking {
        val african = DogBreed(name = "African", subBreeds = "", isFavourite = false)
        dogBreedDao.insertDogBreed(african)
        dogBreedDao.updateDogBreeds("Akita",true)

        val job = async {
            assert(dogBreedDao.getOneDogBreed("African").isFavourite)
        }
        job.cancelAndJoin()
    }
}