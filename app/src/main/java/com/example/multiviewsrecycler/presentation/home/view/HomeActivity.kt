package com.example.multiviewsrecycler.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiviewsrecycler.common.BaseAdapter
import com.example.multiviewsrecycler.databinding.ActivityMainBinding
import com.example.multiviewsrecycler.databinding.NumberViewLayoutBinding
import com.example.multiviewsrecycler.presentation.home.viewModel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    var mAdapter = BaseAdapter<String>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val hHomeViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        home view binding
        */
        hSetViewContent()

        mAdapter.listOfItems = hHomeViewModel.hList

        hRecyclerCallBack()

        hRecyclerBinding()

        hSetUpRecyclerView()


    }

    private fun hRecyclerBinding() {
        mAdapter.expressionOnCreateViewHolder = { viewGroup ->
            //Inflate the layout and send it to the adapter
            NumberViewLayoutBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup,
                false)
        }
    }

    private fun hRecyclerCallBack() {
        mAdapter.expressionViewHolderBinding = { eachItem, viewBinding ->
            //eachItem will provide the each item in the list, in this case its a string type
            //cast the viewBinding with your layout binding class
            val view = viewBinding as NumberViewLayoutBinding
            view.hTvNumber.text = eachItem
            //you can use click listener on root or any button
            view.root.setOnClickListener {
                //Click item value is eachItem
            }
        }
    }

    private fun hSetUpRecyclerView() {

        //finally put the adapter to recyclerview
        binding.hRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun hSetViewContent() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}