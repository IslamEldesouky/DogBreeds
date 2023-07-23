package com.simplesurance.dogbreed.di

import com.simplesurance.dogbreed.domain.repository.DogBreedRepository
import com.simplesurance.dogbreed.domain.usecase.dogBreedImages.DogBreedImagesUseCaseImpl
import com.simplesurance.dogbreed.domain.usecase.dogBreeds.DogBreedUseCaseImpl
import com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds.FavouriteDogBreedFavouriteDogBreedUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideDogBreedsUseCase(repository: DogBreedRepository): DogBreedUseCaseImpl {
        return DogBreedUseCaseImpl(repository)
    }

    @Provides
    fun provideDogBreedsImagesUseCase(repository: DogBreedRepository): DogBreedImagesUseCaseImpl {
        return DogBreedImagesUseCaseImpl(repository)
    }

    @Provides
    fun provideFavouriteDogBreedsUseCase(repository: DogBreedRepository): FavouriteDogBreedFavouriteDogBreedUseCaseImpl {
        return FavouriteDogBreedFavouriteDogBreedUseCaseImpl(repository)
    }
}