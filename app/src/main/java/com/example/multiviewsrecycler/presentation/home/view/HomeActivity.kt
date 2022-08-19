package com.example.multiviewsrecycler.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiviewsrecycler.common.BaseAdapter
import com.example.multiviewsrecycler.common.DataState
import com.example.multiviewsrecycler.databinding.ActivityMainBinding
import com.example.multiviewsrecycler.databinding.ApiViewLayoutBinding
import com.example.multiviewsrecycler.domain.dto.EntryDto
import com.example.multiviewsrecycler.presentation.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    var hRecyclerAdapter = BaseAdapter<EntryDto>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val hHomeViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hSetViewContent()

        hSubscribeObserver()

        hRecyclerCallBack()

        hRecyclerBinding()

        hSetUpRecyclerView()

    }

    private fun hSubscribeObserver() {

        hHomeViewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    displayProgressBar(false)
                    hRecyclerAdapter.listOfItems = dataState.data as MutableList<EntryDto>
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }

            }

        }

    }


    private fun hRecyclerBinding() {
        hRecyclerAdapter.hOnCreateViewHolder = { viewGroup ->
            ApiViewLayoutBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup,
                false)
        }
    }

    private fun hRecyclerCallBack() {
        hRecyclerAdapter.hViewHolderBinding = { eachItem, viewBinding ->
            val view = viewBinding as ApiViewLayoutBinding
            view.hApiName.text = eachItem.api
            view.hApiDescription.text = eachItem.description
            view.hApiUrl.text = eachItem.link
            view.root.setOnClickListener {

            }
        }
    }

    private fun hSetUpRecyclerView() {
        binding.hRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = hRecyclerAdapter
        }
    }

    private fun hSetViewContent() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String?) {
        if (message != null) binding.text.text = message else binding.text.text = "Unknown error."
    }


}