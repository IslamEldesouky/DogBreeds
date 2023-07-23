package com.simplesurance.viewModelTest


import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.domain.usecase.favouriteDogBreeds.FavouriteDogBreedFavouriteDogBreedUseCaseImpl
import com.simplesurance.dogbreed.presentation.dogBreeds.FavouriteDogBreedsViewModel
import com.simplesurance.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
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
class FavouriteDogBreedViewModelTest {

    private lateinit var viewModel: FavouriteDogBreedsViewModel

    @Mock
    private lateinit var dogBreedsUseCase: FavouriteDogBreedFavouriteDogBreedUseCaseImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavouriteDogBreedsViewModel(dogBreedsUseCase)
    }

    @Test
    fun fetchDogBreedsSuccess() = testScope.runTest {
        val akita = DogBreed(name = "Akita", subBreeds = "", isFavourite = true)
        Mockito.lenient().`when`(dogBreedsUseCase.getFavouriteDogBreeds())
            .thenReturn(listOf(akita, akita))
        viewModel.getDogBreeds()
        assert(viewModel.favDogBreeds.size==2)
    }
}