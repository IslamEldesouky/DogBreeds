package com.simplesurance.dogbreed.presentation.dogBreeds

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.usecase.dogBreeds.DogBreedUseCase
import com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds.FavouriteDogBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedsViewModel @Inject constructor(
    private val dogBreedUseCase: DogBreedUseCase,
) :
    ViewModel() {

    private val _dogBreedFlow: MutableStateFlow<Resource<List<DogBreed>?>> =
        MutableStateFlow(Resource.success(null))

    // initializing a StateFlow which will be used by the fragment if any change occurs
    val dogBreedFlow: StateFlow<Resource<List<DogBreed>?>> = _dogBreedFlow

    var dogList : List<DogBreed> = mutableListOf<DogBreed>() // to use in testing

    fun getDogBreeds() {
        viewModelScope.launch {
            try {
                _dogBreedFlow.value = Resource.Loading
                val result = dogBreedUseCase.getDogBreeds()
                if (result != null) {
                    dogList = result
                }
                if (!result.isNullOrEmpty()) {
                    _dogBreedFlow.value = Resource.Success(result)
                } else {
                    _dogBreedFlow.value = Resource.empty()
                }
            } catch (e: Exception) {
                _dogBreedFlow.value = Resource.Failure(e)
            }
        }
    }
}