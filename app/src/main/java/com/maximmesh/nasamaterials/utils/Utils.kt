package com.maximmesh.nasamaterials.utils

import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment

const val NASA_DOMAIN = "https://api.nasa.gov/"
const val NASA_ENDPOINT = "planetary/apod"
const val API_KEY = "api_key"
const val URI_WIKI = "https://en.wikipedia.org/wiki/"
const val SHARED_PREF_THEME = "sharedPreferenceTheme"
const val KEY_THEME_ID = "keyThemeId"


const val CRASH_ITEM_COUNT = 100500
const val DURATION_FOR_ITEMS_CRASH = 4000L
const val DURATION_FOR_SCREEN = 5000L
const val DURATION_FOR_EARTH = 2000L
const val DURATION_FOR_MARS = 200L
const val DURATION_FOR_SYSTEM = 200L

const val HEAD_TEXT_WHEN_U_PRESS_PLUS = "We know u need NASA picture app"

const val DESCRIPTION_WHEN_U_RESS_PLUS =
    "so we put a wiki search and note-taking feature  " +
            "in nasa app\nso u can write note while look picture " +
            "while using search wiki while planets spin"

const val DELAY_FOR_START_MAIN_ACTIVITY = 2000L
const val WEIGHT_OF_PROGRESS_BAR = 2000L
const val LENGTH_OF_PROGRESS_BAR = 2000
const val PAUSE_MOMENT_PROGRESS_BAR = 1950



fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.BOTTOM, 0, 250)
        show()
    }
}