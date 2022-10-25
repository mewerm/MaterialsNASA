package com.maximmesh.nasamaterials.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximmesh.nasamaterials.BuildConfig.NASA_API_KEY
import com.maximmesh.nasamaterials.repository.network.entities.PictureOfDayDTO
import com.maximmesh.nasamaterials.repository.network.PODRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()

) : ViewModel() {

    fun getData(): LiveData<AppState> {
        sendServerRequest(null)
        return liveDataForViewToObserve
    }

    private fun sendServerRequest(date: LocalDate?) {
        liveDataForViewToObserve.value = AppState.Loading(null)

        val apiKey: String = NASA_API_KEY

        if (apiKey.isBlank()) {
            liveDataForViewToObserve.value = AppState.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(object :
                Callback<PictureOfDayDTO> {
                override fun onResponse(
                    call: Call<PictureOfDayDTO>,
                    response: Response<PictureOfDayDTO>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value = AppState.Success(response.body()!!)
                    } else {
                        val message = response.message()

                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                AppState.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                AppState.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<PictureOfDayDTO>, t: Throwable) {
                    liveDataForViewToObserve.value = AppState.Error(t)
                }
            })
        }
    }
}
