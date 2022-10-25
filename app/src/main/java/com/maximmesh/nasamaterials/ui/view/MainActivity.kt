package com.maximmesh.nasamaterials.ui.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.maximmesh.nasamaterials.R
import com.maximmesh.nasamaterials.databinding.ActivityMainBinding
import com.maximmesh.nasamaterials.repository.ShredPrefSave
import com.maximmesh.nasamaterials.ui.viewpager.ViewPagerFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            doFragmentNavigate(PictureOfTheDayFragment())
        }
        initThemePref()
        itemMenuSelect()
    }

    private fun itemMenuSelect() {
        binding.bottomAppBar.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.app_bar_settings -> {
                    doFragmentNavigate(SettingsFragment())
                }
                R.id.action_view_planets -> {
                    doFragmentNavigate(ViewPagerFragment())
                }
                else -> {
                    doFragmentNavigate(PictureOfTheDayFragment())
                }
            }
            true
        })
    }

    private fun doFragmentNavigate(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun initThemePref() {
        val themeStorage = ShredPrefSave(this.application)
        themeStorage.themeID.let {
            when (it) {
                0 -> {
                    setTheme(R.style.BaseTheme)
                    window.statusBarColor = getColor(R.color.colorPrimary)
                }
                1 -> {
                    setTheme(R.style.GreenTheme)
                    window.statusBarColor = getColor(R.color.greenPrimaryColor)
                }
                2 -> {
                    setTheme(R.style.SandTheme)
                    window.statusBarColor = getColor(R.color.sandPrimaryColor)
                }
            }
        }
    }
}