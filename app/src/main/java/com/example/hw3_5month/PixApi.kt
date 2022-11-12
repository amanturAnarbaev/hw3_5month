package com.example.hw3_5month

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "31176004-08ce55d6e1c04ddbc16e5cdf5",
        @Query("q") q: String,
        @Query("page")page : Int = 1,
        @Query("per_page") per_page :Int =20
    ): Call<PixModel>

}