package com.simplesurance.dogbreed.di

import android.content.Context
import androidx.room.Room
import com.simplesurance.dogbreed.data.local.db.Constants.DB_NAME
import com.simplesurance.dogbreed.data.local.db.DogBreedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DogBreedDatabase::class.java,
        DB_NAME
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries().build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: DogBreedDatabase) =
        db.dogBreedsDao() // The reason we can implement a Dao for the database
}