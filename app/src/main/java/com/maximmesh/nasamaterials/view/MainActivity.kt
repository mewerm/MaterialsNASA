package com.maximmesh.nasamaterials.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximmesh.nasamaterials.R
import com.maximmesh.nasamaterials.databinding.ActivityMainBinding
import com.maximmesh.nasamaterials.model.repository.ShredPrefSave

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initThemePref()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

    private fun initThemePref() {
        val themeStorage = ShredPrefSave(this.application)
        themeStorage.themeID.let {
            when (it) {
                0 -> {
                    setTheme(R.style.BaseTheme)
                    window.statusBarColor = resources.getColor(R.color.colorPrimary)
                }
                1 -> {
                    setTheme(R.style.GreenTheme)
                    window.statusBarColor = resources.getColor(R.color.greenPrimaryColor)
                }
                2 -> {
                    setTheme(R.style.SandTheme)
                    window.statusBarColor = resources.getColor(R.color.sandPrimaryColor)
                }
            }
        }
    }
}