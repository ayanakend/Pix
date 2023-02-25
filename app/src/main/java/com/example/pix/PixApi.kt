package com.example.pix

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "33928442-f6189cbd449976282ada39a4b",
        @Query("q") pictureWord: String,
        @Query("per_page") perPage:Int = 5,
        @Query("page") page:Int
    ):Call<PixModel>
}