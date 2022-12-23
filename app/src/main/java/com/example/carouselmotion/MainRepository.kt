package com.example.carouselmotion

import kotlinx.coroutines.flow.flow

class MainRepository(private val apiService: ApiService) {
    suspend fun fetchPodcast() = flow {
        val map = HashMap<String, String>()
        map["api_key"] = BuildConfig.API_KEY
        val response = apiService.getMovieDate(map)
        if (response.isSuccessful)
            emit(response.body()!!)
    }


}