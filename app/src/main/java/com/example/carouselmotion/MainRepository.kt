package com.example.carouselmotion

import kotlinx.coroutines.flow.flow

class MainRepository(private val apiService: ApiService) {
    suspend fun fetchPodcast() = flow<MovieData> {
        val map = HashMap<String, String>()
        map["api_key"] = "1d9b898a212ea52e283351e521e17871"
        val response = apiService.getMovieDate(map)
        if (response.isSuccessful)
            emit(response.body()!!)
    }


}