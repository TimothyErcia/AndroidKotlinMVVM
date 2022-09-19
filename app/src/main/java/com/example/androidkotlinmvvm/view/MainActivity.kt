package com.example.androidkotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinmvvm.R
import com.example.androidkotlinmvvm.adapter.PhotosItemAdapter
import com.example.androidkotlinmvvm.model.Photos
import com.example.androidkotlinmvvm.viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PhotosItemAdapter
    private lateinit var photosViewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeData()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun initializeData() {
        adapter = PhotosItemAdapter(this)
        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        photosViewModel.getPhotos().observe(this, object : Observer<List<Photos>> {
            override fun onChanged(t: List<Photos>?) {
                if(t != null) {
                    adapter.setPhotoList(t)
                }
            }
        })
        photosViewModel.getDataFromAPI()
    }
}