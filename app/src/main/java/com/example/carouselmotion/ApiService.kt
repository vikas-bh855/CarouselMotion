package com.example.carouselmotion

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("movie/upcoming")
    suspend fun getMovieDate(@QueryMap map: Map<String, String>): Response<MovieData>
}


object ApiModule {
     const val baseUrl = "https://api.themoviedb.org/3/"

    @Synchronized
    fun getApiService(): ApiService {
        val retrofit = Retrofit.Builder().apply {
            baseUrl(baseUrl)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        return retrofit.create(ApiService::class.java)
    }
}
