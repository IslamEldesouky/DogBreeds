package com.simplesurance.dogbreed.di

import com.simplesurance.dogbreed.domain.repository.DogBreedRepository
import com.simplesurance.dogbreed.domain.usecase.dogBreedImages.DogBreedImagesUseCase
import com.simplesurance.dogbreed.domain.usecase.dogBreeds.DogBreedUseCase
import com.simplesurance.dogbreed.domain.usecase.dogBreeds.UseCase
import com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds.FavouriteDogBreedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideDogBreedsUseCase(repository: DogBreedRepository): DogBreedUseCase {
        return DogBreedUseCase(repository)
    }

    @Provides
    fun provideDogBreedsImagesUseCase(repository: DogBreedRepository): DogBreedImagesUseCase {
        return DogBreedImagesUseCase(repository)
    }

    @Provides
    fun provideFavouriteDogBreedsUseCase(repository: DogBreedRepository): FavouriteDogBreedUseCase {
        return FavouriteDogBreedUseCase(repository)
    }
}