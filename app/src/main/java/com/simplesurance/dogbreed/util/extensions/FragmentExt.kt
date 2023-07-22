package com.simplesurance.dogbreed.util.extensions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.simplesurance.dogbreed.R
import com.simplesurance.dogbreed.databinding.FragmentDogBreedsBinding


fun Fragment.showErrorDialog(message : String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
}
fun Fragment.showLoadingDialog(binding: FragmentDogBreedsBinding){
    binding.loading.visibility = View.VISIBLE
    }

fun Fragment.hideLoadingDialog(binding: FragmentDogBreedsBinding){
    binding.loading.visibility = View.GONE
}