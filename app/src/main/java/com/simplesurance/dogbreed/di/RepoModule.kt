package com.simplesurance.dogbreed.di

import com.simplesurance.dogbreed.data.local.LocalDataSource
import com.simplesurance.dogbreed.data.remote.RemoteDataSource
import com.simplesurance.dogbreed.data.repository.DogBreedRepositoryImpl
import com.simplesurance.dogbreed.domain.repository.DogBreedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRepo(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): DogBreedRepository {
        return DogBreedRepositoryImpl(remoteDataSource, localDataSource)
    }
}