package com.simplesurance.dogbreed.presentation.dogBreedsDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.simplesurance.dogbreed.R
import com.simplesurance.dogbreed.databinding.DogImageItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class DogBreedDetailsAdapter(dogBreedImages: List<String>) :
    SliderViewAdapter<DogBreedDetailsAdapter.ViewHolder>() {

    // on below line we are creating a
    // new array list and initializing it.
    private var sliderList: List<String> = dogBreedImages

    // on below line we are calling get method
    override fun getCount(): Int {

        return sliderList.size
    }

    // on below line we are calling on create view holder method.
    override fun onCreateViewHolder(parent: ViewGroup?): DogBreedDetailsAdapter.ViewHolder {
        val itemBinding =
            DogImageItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return DogBreedDetailsAdapter.ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: DogBreedDetailsAdapter.ViewHolder?, position: Int) {
        if (viewHolder != null) {
            Glide.with(viewHolder.itemView).load(sliderList.get(position)).fitCenter()
                .diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).placeholder(R.drawable.placeholder_empty)
                .into(viewHolder.imageView)
        }
    }

    class ViewHolder(private val dogImageItemBinding: DogImageItemBinding) :
        SliderViewAdapter.ViewHolder(dogImageItemBinding.root) {
        var imageView: ImageView = dogImageItemBinding.myimage
    }
}