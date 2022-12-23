package com.example.carouselmotion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carouselmotion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            carousel.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        CarouselActivity::class.java
                    )
                )
            }
            dotLoader.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        DotLoader::class.java
                    )
                )
            }
            swipeOnTouch.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        SwipeActivity::class.java
                    )
                )
            }
        }
    }
}
