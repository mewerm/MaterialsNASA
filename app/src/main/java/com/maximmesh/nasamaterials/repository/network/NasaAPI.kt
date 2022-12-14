package com.maximmesh.nasamaterials.repository.network

import com.maximmesh.nasamaterials.repository.network.entities.PictureOfDayDTO
import com.maximmesh.nasamaterials.utils.API_KEY
import com.maximmesh.nasamaterials.utils.NASA_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {
    @GET(NASA_ENDPOINT)
    fun getPictureOfTheDay(@Query(API_KEY) apiKey: String): Call<PictureOfDayDTO>

}