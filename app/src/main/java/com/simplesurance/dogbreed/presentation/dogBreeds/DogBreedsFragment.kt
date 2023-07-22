package com.simplesurance.dogbreed.presentation.dogBreeds

import android.app.Dialog
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplesurance.dogbreed.data.Resource
import com.simplesurance.dogbreed.databinding.FragmentDogBreedsBinding
import com.simplesurance.dogbreed.domain.model.DogBreed
import com.simplesurance.dogbreed.util.extensions.hideLoadingDialog
import com.simplesurance.dogbreed.util.extensions.showLoadingDialog
import com.stc.newsapp.presentation.home.adapter.DogBreedsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class DogBreedsFragment : Fragment(),DogBreedsAdapter.ItemSelected {

    private val viewModel: DogBreedsViewModel by viewModels()
    lateinit var binding : FragmentDogBreedsBinding
    lateinit var loadingDialog : Dialog
    lateinit var adapter: DogBreedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogBreedsBinding.inflate(inflater)
        adapter = DogBreedsAdapter(this@DogBreedsFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = Dialog(requireContext())

        lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.dogBreedFlow.collect{
                    when(it){
                        is Resource.Success -> initView(it.value)
                        is Resource.Loading -> {
                            showLoadingDialog(binding)
                        }
                        is Resource.Empty -> showEmptyView()
                        is Resource.Failure -> showErrorView(it.exception)
                        else -> {}
                    }
                }
            }
        }
    }

    private fun initView(dogBreedList: List<DogBreed>?){
        hideLoadingDialog(binding)
        binding.dogRv.layoutManager = LinearLayoutManager(this.requireContext())
        binding.dogRv.adapter = adapter
        adapter.submitList(dogBreedList)
    }

    private fun showEmptyView(){
        hideLoadingDialog(binding)
        Log.d("RESULT", "EMPTY")
    }

    private fun showErrorView(e:Throwable){
        hideLoadingDialog(binding)
        Log.d("DOG BREED ERROR", e.message.toString())
    }

    override fun itemSelected(item: DogBreed?) {}
}