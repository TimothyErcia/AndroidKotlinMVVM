package com.example.androidkotlinmvvm.repository

import com.example.androidkotlinmvvm.model.Photos
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PhotoAPI {

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>
}