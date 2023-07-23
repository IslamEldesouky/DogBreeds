package com.simplesurance.dogbreed.util.extensions

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.simplesurance.dogbreed.databinding.FragmentDogBreedDetailsBinding
import com.simplesurance.dogbreed.databinding.FragmentDogBreedsBinding
import com.simplesurance.dogbreed.databinding.FragmentFavouriteDogBreedsBinding


fun Fragment.showErrorDialog(message : String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
}
fun Fragment.showLoadingDialog(binding: FragmentDogBreedsBinding){
    binding.loading.visibility = View.VISIBLE
    }

fun Fragment.hideLoadingDialog(binding: FragmentDogBreedsBinding){
    binding.loading.visibility = View.GONE
}

fun Fragment.showLoadingDetailsDialog(binding: FragmentDogBreedDetailsBinding) {
    binding.loading.visibility = View.VISIBLE
}

fun Fragment.hideLoadingDetailsDialog(binding: FragmentDogBreedDetailsBinding) {
    binding.loading.visibility = View.GONE
}

fun Fragment.showLoadingFavouriteDialog(binding: FragmentFavouriteDogBreedsBinding) {
    binding.loading.visibility = View.VISIBLE
}

fun Fragment.hideLoadingFavouriteDialog(binding: FragmentFavouriteDogBreedsBinding) {
    binding.loading.visibility = View.GONE
}