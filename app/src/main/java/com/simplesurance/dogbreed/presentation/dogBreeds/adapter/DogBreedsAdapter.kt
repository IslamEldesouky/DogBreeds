package com.stc.newsapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simplesurance.dogbreed.databinding.DogItemBinding
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.util.getSubBreedsCount
import java.util.Locale

class DogBreedsAdapter(private val itemSelected: ItemSelected) :
    ListAdapter<DogBreed, DogBreedsAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            DogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, itemSelected) }
    }

    class ViewHolder(private val itemBinding: DogItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(dog: DogBreed, listener: ItemSelected) {
            itemBinding.dog = dog
            itemBinding.dogBreedNameTv.text = dog.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            itemBinding.dogSubBreedNameTv.text = getSubBreedsCount(dog.subBreeds)
            itemBinding.dogItemLayout.setOnClickListener { listener.itemSelected(dog) }
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<DogBreed>() {
        override fun areItemsTheSame(
            oldItem: DogBreed,
            newItem: DogBreed
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: DogBreed,
            newItem: DogBreed
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ItemSelected {
        fun itemSelected(item: DogBreed?)
    }
}