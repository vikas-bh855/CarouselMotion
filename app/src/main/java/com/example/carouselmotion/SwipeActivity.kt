package com.example.carouselmotion

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.carouselmotion.databinding.SwipeActivityBinding
import kotlin.math.abs


class SwipeActivity : AppCompatActivity() {

    var moveX = 0f
    var moveY = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SwipeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            var dX = 0f
            var dY = 0f
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay
                .getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels
            cardView.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dX = view.x - motionEvent.rawX
                        dY = view.y - motionEvent.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        Log.d("TAG", "rawY: ${motionEvent.rawY}")
                        Log.d("TAG", "Y: ${motionEvent.y}")
                        moveX = motionEvent.getRawX() + dX
                        moveY = motionEvent.getRawY() + dY
                        val rotation = (moveX / 3600) * 100
                        view.animate().apply {
                            if (moveX > 100f || moveX < -100f) {
                                x(moveX)
                                rotation(rotation)
                            } else if (moveY > 100f || moveY < -100f) y(moveY)
                        }.setDuration(0)
                            .start()
                        Log.d("TAG", "moveX: $moveX")
                        Log.d("TAG", "moveY: $moveY")
                        Log.d("TAG", "rotation: $rotation")
                    }
                    MotionEvent.ACTION_UP,
                    MotionEvent.ACTION_BUTTON_RELEASE,
                    MotionEvent.ACTION_CANCEL -> {
                        if (moveX > 100f || moveX < -100f) {
                            if (moveX > 100f && moveX > (width * 75) / 100)
                                Log.d("TAG", "bind: swipe to right")
                            else if (moveX < -100f && abs(moveX) > (width * 75) / 100)
                                Log.d("TAG", "bind: swipe to left")
                            else {
                                view.animate().x(0f).rotation(0f).setDuration(200).start()
                            }
                        } else {
                            if (moveY > displayMetrics.heightPixels / 4) {
                                Log.d("TAG", "bottom")
                                view.animate().setDuration(300)
                                    .translationY(displayMetrics.heightPixels / 2f)
                            } else {
                                Log.d("TAG", "top")
                                view.animate().setDuration(300).translationY(0f)
                            }
                            Log.d("TAG", "bind: animate")
                            view.animate().x(0f).rotation(0f).setDuration(200).start()
                        }
                    }
                }
                return@setOnTouchListener true
            }
        }
    }
}

