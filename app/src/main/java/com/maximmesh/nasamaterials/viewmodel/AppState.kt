package com.maximmesh.nasamaterials.viewmodel

import com.maximmesh.nasamaterials.model.entities.PictureOfDayDTO


sealed class AppState {
    data class Success(val serverResponseData: PictureOfDayDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}