package com.maximmesh.nasamaterials.ui.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximmesh.nasamaterials.R
import com.maximmesh.nasamaterials.databinding.ActivitySplashScreenBinding
import com.maximmesh.nasamaterials.utils.DELAY_FOR_START_MAIN_ACTIVITY
import com.maximmesh.nasamaterials.utils.LENGTH_OF_PROGRESS_BAR
import com.maximmesh.nasamaterials.utils.PAUSE_MOMENT_PROGRESS_BAR
import com.maximmesh.nasamaterials.utils.WEIGHT_OF_PROGRESS_BAR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreen : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.purple_200)

        CoroutineScope(Dispatchers.Main).launch {
            binding.splashProgressBar.max = LENGTH_OF_PROGRESS_BAR
            val value = PAUSE_MOMENT_PROGRESS_BAR
            ObjectAnimator.ofInt(binding.splashProgressBar, getString(R.string.progress), value)
                .setDuration(WEIGHT_OF_PROGRESS_BAR).start()
            delay(DELAY_FOR_START_MAIN_ACTIVITY)
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }
    }
}