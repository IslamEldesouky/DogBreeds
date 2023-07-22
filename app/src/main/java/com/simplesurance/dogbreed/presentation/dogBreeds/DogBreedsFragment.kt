package com.simplesurance.dogbreed.presentation.dogBreeds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.databinding.FragmentDogBreedsBinding
import com.simplesurance.dogbreed.domain.model.DogBreed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class DogBreedsFragment : Fragment() {

    private val viewModel: DogBreedsViewModel by viewModels()
    lateinit var binding : FragmentDogBreedsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogBreedsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.dogBreedFlow.collect{
                    when(it){
                        is Resource.Success -> initView(it.value)
                        is Resource.Failure -> showErrorView(it.exception)
                        else -> {}
                    }
                }
            }
        }
    }

    private fun initView(dogBreedList: List<DogBreed>?){
        Log.d("RESULT", dogBreedList?.size.toString())
    }

    fun showEmptyView(){}

    private fun showErrorView(e:Throwable){
        Log.d("DOG BREED =ERROR", e.message.toString())
    }
}