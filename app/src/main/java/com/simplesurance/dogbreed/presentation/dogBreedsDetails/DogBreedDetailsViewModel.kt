package com.simplesurance.dogbreed.presentation.dogBreedsDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.usecase.dogBreedImages.DogBreedImagesUseCase
import com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds.FavouriteDogBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedDetailsViewModel @Inject constructor(
    private val dogBreedImagesUseCase: DogBreedImagesUseCase,
    private val favouriteDogBreedUseCase: FavouriteDogBreedUseCase
) : ViewModel() {

    private val _dogBreedImagesFlow: MutableStateFlow<Resource<List<String>?>> =
        MutableStateFlow(Resource.success(null))

    // initializing a StateFlow which will be used by the fragment if any change occurs
    val dogBreedImagesFlow: StateFlow<Resource<List<String>?>> = _dogBreedImagesFlow

    fun getDogBreeds(breedName: String) {
        viewModelScope.launch {
            try {
                _dogBreedImagesFlow.value = Resource.Loading
                val result = dogBreedImagesUseCase.getDogBreedImages(breedName)
                if (!result.isNullOrEmpty()) {
                    _dogBreedImagesFlow.value = Resource.Success(result)
                } else {
                    _dogBreedImagesFlow.value = Resource.empty()
                }
            } catch (e: Exception) {
                Log.d("RESULT", e.message.toString())
                _dogBreedImagesFlow.value = Resource.Failure(e)
            }
        }
    }

    fun updateDogBreed(dogName: String?, isFavourite: Boolean) {
        viewModelScope.launch {
            favouriteDogBreedUseCase.addToFavourites(dogName.toString(), isFavourite)
        }
    }
}