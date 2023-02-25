package com.example.pix

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PixService {

    private var retrofit = Retrofit.Builder().baseUrl(
        "https://pixabay.com/").addConverterFactory(
        GsonConverterFactory.create()).build()

    val api = retrofit.create(PixApi::class.java)
}