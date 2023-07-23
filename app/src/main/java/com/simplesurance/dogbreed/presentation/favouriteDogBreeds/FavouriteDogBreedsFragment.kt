package com.simplesurance.dogbreed.presentation.favouriteDogBreeds

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplesurance.dogbreed.R
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.databinding.FragmentFavouriteDogBreedsBinding
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.presentation.dogBreeds.DogBreedsViewModel
import com.simplesurance.dogbreed.presentation.dogBreeds.FavouriteDogBreedsViewModel
import com.simplesurance.dogbreed.util.extensions.hideLoadingFavouriteDialog
import com.simplesurance.dogbreed.util.extensions.showLoadingFavouriteDialog
import com.stc.newsapp.presentation.home.adapter.DogBreedsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteDogBreedsFragment : Fragment(), DogBreedsAdapter.ItemSelected {

    private val viewModel: FavouriteDogBreedsViewModel by viewModels()
    lateinit var binding: FragmentFavouriteDogBreedsBinding
    lateinit var loadingDialog: Dialog
    lateinit var adapter: DogBreedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteDogBreedsBinding.inflate(inflater)
        adapter = DogBreedsAdapter(this@FavouriteDogBreedsFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = Dialog(requireContext())
        viewModel.getDogBreeds()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dogBreedFlow.collect {
                    when (it) {
                        is Resource.Success -> initView(it.value)
                        is Resource.Loading -> {
                            showLoadingFavouriteDialog(binding)
                        }

                        is Resource.Empty -> showEmptyView()
                        is Resource.Failure -> showErrorView(it.exception)
                        else -> {}
                    }
                }
            }
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initView(dogBreedList: List<DogBreed>?) {
        hideLoadingFavouriteDialog(binding)
        binding.dogRv.layoutManager = LinearLayoutManager(this.requireContext())
        binding.dogRv.adapter = adapter
        adapter.submitList(dogBreedList)
    }

    private fun showEmptyView() {
        hideLoadingFavouriteDialog(binding)
        Toast.makeText(
            this@FavouriteDogBreedsFragment.requireContext(),
            getString(R.string.no_favourites_added),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showErrorView(e: Throwable) {
        hideLoadingFavouriteDialog(binding)
        Toast.makeText(
            this@FavouriteDogBreedsFragment.requireContext(),
            getString(R.string.error_message),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun itemSelected(item: DogBreed?) {
        val bundle = bundleOf(
            "name" to item?.name,
            "sub_breed" to item?.subBreeds,
            "is_favourite" to item?.isFavourite
        )
        findNavController().navigate(
            R.id.action_favouriteDogBreedsFragment_to_dogBreedDetailsFragment,
            bundle
        )
    }
}