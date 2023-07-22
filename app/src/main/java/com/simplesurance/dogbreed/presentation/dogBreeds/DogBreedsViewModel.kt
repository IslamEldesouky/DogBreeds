package com.simplesurance.dogbreed.presentation.dogBreeds

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.usecase.dogBreeds.DogBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedsViewModel @Inject constructor(private val dogBreedUseCase: DogBreedUseCase) :
    ViewModel() {

    private val _dogBreedFlow: MutableStateFlow<Resource<List<DogBreed>?>> =
        MutableStateFlow(Resource.success(null))

    // initializing a StateFlow which will be used by the fragment if any change occurs
    val dogBreedFlow: StateFlow<Resource<List<DogBreed>?>> = _dogBreedFlow
    var dogBreedList: List<DogBreed?> = ArrayList()

    init {
        getDogBreeds()
    }

    fun getDogBreeds() {
        viewModelScope.launch {
            try {
                val result = dogBreedUseCase.getDogBreeds()
                Log.d("RESULTVIEWMODEL", result?.size.toString())
                if (!result.isNullOrEmpty()) {
                    _dogBreedFlow.value = Resource.Success(result)
                    dogBreedList = result
                }
            } catch (e: Exception) {
                Log.d("RESULT", e.localizedMessage.toString())
                _dogBreedFlow.value = Resource.Failure(e)
            }
        }
    }
}