package com.example.carouselmotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class MovieData(val results: List<Results>)

data class Results(
    val poster_path: String,
    val overview: String,
    val title: String
)