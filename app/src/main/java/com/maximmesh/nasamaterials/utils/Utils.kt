package com.maximmesh.nasamaterials.utils

import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment

const val NASA_DOMAIN = "https://api.nasa.gov/"
const val NASA_ENDPOINT ="planetary/apod"
const val API_KEY ="api_key"
const val URI_WIKI ="https://en.wikipedia.org/wiki/"
const val SHARED_PREF_THEME = "sharedPreferenceTheme"
const val KEY_THEME_ID = "keyThemeId"



 fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.BOTTOM, 0, 250)
        show()
    }
}