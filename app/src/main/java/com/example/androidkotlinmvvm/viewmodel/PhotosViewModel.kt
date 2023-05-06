package com.example.androidkotlinmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidkotlinmvvm.model.Photos
import com.example.androidkotlinmvvm.repository.PhotoAPI
import com.example.androidkotlinmvvm.repository.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotosViewModel: ViewModel() {
    private val TAG = "PhotosViewModel"
    private lateinit var photosAPI: PhotoAPI

    private var mPhotoList: MutableLiveData<List<Photos>> = MutableLiveData()
    fun getPhotos(): LiveData<List<Photos>> {
        return mPhotoList
    }

    fun getDataFromAPI() {
        photosAPI = RetrofitInstance.retrofit.create(PhotoAPI::class.java)
        var call: Call<List<Photos>> = photosAPI.getPhotos()
        call.enqueue(object: Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
                if(response.isSuccessful) {
                    mPhotoList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
    }

    var getPhotos2 = liveData<List<Photos>> {
        photosAPI = RetrofitInstance.retrofit.create(PhotoAPI::class.java)
        emit(photosAPI.getPhotos2())
    }

}