package com.simplesurance.dogbreed.di

import com.simplesurance.dogbreed.data.local.LocalDataSource
import com.simplesurance.dogbreed.data.local.LocalDataSourceImpl
import com.simplesurance.dogbreed.data.local.db.DogBreedDao
import com.simplesurance.dogbreed.data.remote.RemoteDataSource
import com.simplesurance.dogbreed.data.remote.RemoteDataSourceImpl
import com.simplesurance.dogbreed.data.remote.service.DogBreedService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(api : DogBreedService) : RemoteDataSource{
        return RemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: DogBreedDao): LocalDataSource {
        return LocalDataSourceImpl(dao)
    }
}