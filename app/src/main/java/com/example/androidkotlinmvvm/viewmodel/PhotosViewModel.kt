package com.example.androidkotlinmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidkotlinmvvm.model.Photos
import com.example.androidkotlinmvvm.repository.PhotoAPI
import com.example.androidkotlinmvvm.repository.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotosViewModel: ViewModel() {

    private var photosAPI: PhotoAPI? = null
    private var mPhotosList: MutableLiveData<List<Photos>> = MutableLiveData();
    fun getPhotos(): LiveData<List<Photos>> {
        return mPhotosList
    }

    fun getDataFromAPI() {
        photosAPI = RetrofitInstance.getInstance()?.create(PhotoAPI::class.java)
        val call = photosAPI?.getPhotos()
        call?.enqueue(object : Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
                if(response.isSuccessful) {
                    mPhotosList.postValue(response.body())
                } else {
                    mPhotosList.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                mPhotosList.postValue(null)
            }
        })
    }

}