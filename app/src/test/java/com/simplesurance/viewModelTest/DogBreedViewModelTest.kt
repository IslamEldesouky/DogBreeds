package com.simplesurance.viewModelTest

import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.usecase.dogBreeds.DogBreedUseCase
import com.simplesurance.dogbreed.presentation.dogBreeds.DogBreedsViewModel
import com.simplesurance.util.CoroutineTestRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DogBreedViewModelTest {

    private lateinit var viewModel: DogBreedsViewModel
    @Mock
    private lateinit var dogBreedsUseCase:DogBreedUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DogBreedsViewModel(dogBreedsUseCase)
    }

    @Test
    fun fetchDogBreedsSuccess() = testScope.runTest {
        val akita = DogBreed(name = "Akita", subBreeds = "", isFavourite = false)
        Mockito.lenient().`when`(dogBreedsUseCase.getDogBreeds()).thenReturn(listOf(akita,akita,akita,akita))
        viewModel.getDogBreeds()
        assert(viewModel.dogList.size==4)
    }
}