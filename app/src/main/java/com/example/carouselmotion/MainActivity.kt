package com.example.carouselmotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.helper.widget.Carousel
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import coil.load

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiService = ApiModule.getApiService()
        val mainRepository = MainRepository(apiService)
        val viewModel =
            ViewModelProvider(this, Factory(mainRepository)).get(MainViewModel::class.java)
        viewModel.fetchMovieData()
        viewModel.movieData.observe(this, {
            val list = it.results.map { it.poster_path }
            findViewById<Carousel>(R.id.carousel).apply {
                setAdapter(object : Carousel.Adapter {
                    override fun count(): Int {
                        return list.size
                    }

                    override fun populate(view: View?, index: Int) {
                        if (view is ImageFilterView) {
                            view.load("https://image.tmdb.org/t/p/w220_and_h330_face" + list[index])
                        }
                    }
                    override fun onNewItem(index: Int) {

                    }

                })
                jumpToIndex(1)
            }
        })

    }
}

class Factory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}