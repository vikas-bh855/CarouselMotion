package com.example.carouselmotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.helper.widget.Carousel
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import coil.load
import com.example.carouselmotion.databinding.DotLoaderBinding
import kotlinx.coroutines.*
import java.io.FileOutputStream

class DotLoader : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DotLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            motionDot.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int
                ) {
                    Log.d(TAG, "onTransitionStarted: ")
                }

                override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {
                    Log.d(TAG, "onTransitionChange: ")
                }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    Log.d(TAG, "onTransitionCompleted: ${currentId}: ${R.id.start}")
                    Log.d(TAG, "onTransitionCompleted: ${currentId}: ${R.id.intermediate1}")
                    Log.d(TAG, "onTransitionCompleted: ${currentId}: ${R.id.intermediate2}")
                    Log.d(TAG, "onTransitionCompleted: ${currentId}: ${R.id.end}")
                    if (currentId == R.id.start)
                        motionLayout?.getTransition(R.id.first)?.autoTransition =
                            MotionScene.Transition.AUTO_ANIMATE_TO_END
                    else if (currentId == R.id.intermediate1)
                        motionLayout?.getTransition(R.id.second)?.autoTransition =
                            MotionScene.Transition.AUTO_ANIMATE_TO_END
                    else if (currentId == R.id.intermediate2)
                        motionLayout?.getTransition(R.id.third)?.autoTransition =
                            MotionScene.Transition.AUTO_ANIMATE_TO_END
                    else if (currentId == R.id.end)
                        motionLayout?.getTransition(R.id.fourth)?.autoTransition =
                            MotionScene.Transition.AUTO_ANIMATE_TO_END
                }

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout?,
                    triggerId: Int,
                    positive: Boolean,
                    progress: Float
                ) {
                    Log.d(TAG, "onTransitionTrigger: ")
                }

            })
        }
    }

    private val TAG = "DotLoader"
}