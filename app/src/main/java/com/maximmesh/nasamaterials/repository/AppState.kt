package com.maximmesh.nasamaterials.repository

import com.maximmesh.nasamaterials.repository.network.entities.PictureOfDayDTO


sealed class AppState {
    data class Success(val serverResponseData: PictureOfDayDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}