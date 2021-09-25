package com.example.carouselmotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainViewModel(val repository: MainRepository) : ViewModel() {
    private var _movieData: MutableLiveData<MovieData> = MutableLiveData()
    val movieData: LiveData<MovieData> = _movieData
    private val scope = CoroutineScope(Dispatchers.IO + Job())

     fun fetchMovieData() {
        scope.launch {
            repository.fetchPodcast().collect {
                _movieData.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (scope.isActive)
            scope.cancel()
    }
}