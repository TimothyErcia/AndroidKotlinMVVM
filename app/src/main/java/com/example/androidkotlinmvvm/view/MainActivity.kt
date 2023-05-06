package com.example.androidkotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinmvvm.R
import com.example.androidkotlinmvvm.adapter.PhotosItemAdapter
import com.example.androidkotlinmvvm.model.Photos
import com.example.androidkotlinmvvm.viewmodel.PhotosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PhotosItemAdapter
    private lateinit var photosViewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = PhotosItemAdapter(this)
        recyclerView.adapter = adapter
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                initializeData()
            }
        }
    }

    private fun initializeData() {
        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
//        photosViewModel.getPhotos().observe(this, Observer {
//            adapter.setPhotoArrayList(it)
//        })

//        withContext(IO) {
//            photosViewModel.getDataFromAPI()
//        }
        // EASIER IMPLEMENTATION
        photosViewModel.getPhotos2.observe(this, Observer {
            adapter.setPhotoList(it)
        })
    }
}