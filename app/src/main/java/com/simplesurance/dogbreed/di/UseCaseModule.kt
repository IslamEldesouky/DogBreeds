package com.simplesurance.dogbreed.di

import com.simplesurance.dogbreed.domain.repository.DogBreedRepository
import com.simplesurance.dogbreed.domain.usecase.dogBreedImages.DogBreedImagesUseCase
import com.simplesurance.dogbreed.domain.usecase.dogBreeds.DogBreedUseCase
import com.simplesurance.dogbreed.domain.usecase.dogBreeds.UseCase
import com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds.FavouriteDogBreedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideDogBreedsUseCase(repository: DogBreedRepository): UseCase {
        return DogBreedUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDogBreedsImagesUseCase(repository: DogBreedRepository): com.simplesurance.dogbreed.domain.usecase.dogBreedImages.UseCase {
        return DogBreedImagesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFavouriteDogBreedsUseCase(repository: DogBreedRepository): com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds.UseCase {
        return FavouriteDogBreedUseCase(repository)
    }
}