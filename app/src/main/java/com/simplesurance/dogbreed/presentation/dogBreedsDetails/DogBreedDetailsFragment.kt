package com.simplesurance.dogbreed.presentation.dogBreedsDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.simplesurance.dogbreed.R
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.databinding.FragmentDogBreedDetailsBinding
import com.simplesurance.dogbreed.presentation.dogBreedsDetails.adapter.DogBreedDetailsAdapter
import com.simplesurance.dogbreed.util.extensions.hideLoadingDetailsDialog
import com.simplesurance.dogbreed.util.extensions.showLoadingDetailsDialog
import com.simplesurance.dogbreed.util.getSubBreedsCountV2
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class DogBreedDetailsFragment : Fragment() {

    private val viewModel: DogBreedDetailsViewModel by viewModels()
    private lateinit var adapter: DogBreedDetailsAdapter
    private lateinit var binding: FragmentDogBreedDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogBreedDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dogBreedImagesFlow.collect {
                    when (it) {
                        is Resource.Success -> it.value?.let { it1 -> initRv(it1) }
                        is Resource.Empty -> {
                            hideLoadingDetailsDialog(binding)
                            Toast.makeText(
                                this@DogBreedDetailsFragment.requireContext(),
                                getString(R.string.details_empty_images_message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is Resource.Loading -> {
                            showLoadingDetailsDialog(binding)
                        }

                        is Resource.Failure -> {}
                    }
                }
            }
        }
    }

    private fun initView() {
        val name = arguments?.getString("name")
        val subBreed = arguments?.getString("sub_breed")
        var isFav = arguments?.getBoolean("is_favourite")
        viewModel.getDogBreeds(name.toString())
        binding.tvTitle.text = name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        if (!getSubBreedsCountV2(subBreed.toString()).equals("0")) {
            binding.tvSubBreeds.text = subBreed
            Log.d("SUB BREEDS",subBreed.toString())
        }
        if (isFav == true) {
            binding.ivIsFavourite.background =
                ContextCompat.getDrawable(this.requireContext(), (R.drawable.baseline_favorite_24))
        } else {
            binding.ivIsFavourite.background = ContextCompat.getDrawable(
                this.requireContext(),
                (R.drawable.baseline_favorite_border_24)
            )
        }
        binding.ivIsFavourite.setOnClickListener {
            if (isFav == true) {
                binding.ivIsFavourite.background =
                    ContextCompat.getDrawable(
                        this.requireContext(),
                        (R.drawable.baseline_favorite_border_24)
                    )
                isFav = false
                viewModel.updateDogBreed(name.toString(), false)
            } else {
                binding.ivIsFavourite.background = ContextCompat.getDrawable(
                    this.requireContext(),
                    (R.drawable.baseline_favorite_24)
                )
                isFav = true
                viewModel.updateDogBreed(name.toString(), true)
            }
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initRv(dogBreedImages: List<String>) {
        hideLoadingDetailsDialog(binding)
        adapter = DogBreedDetailsAdapter(dogBreedImages)
        binding.slider.setSliderAdapter(adapter)
        binding.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        binding.slider.isAutoCycle = true
        binding.slider.startAutoCycle()
    }
}