package com.xjm.weatherdoctors.data.service

import com.xjm.weatherdoctors.domain.model.DarkSkyRequest
import com.xjm.weatherdoctors.domain.model.DarkSkyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 *
 *   This class is a Weather repository that is used to call the data source , called by use case
 */
class WeatherRepository(private val dataSource: WeatherDataSource) {

// =====================================================================================================================
// Attributes
// =====================================================================================================================

    companion object {
        const val API_KEY = "d34d5bc5f63fd0b05110dd9a4cb92b97"
        const val UNIT_US = "us"
        const val LANGUAGE = "es"
    }

// =====================================================================================================================
// Service methods
// =====================================================================================================================

    /**
     * Called to get the current weather and get the callback
     */
    suspend fun getCurrentWeather(
        model: DarkSkyRequest,
        onSuccess: (daily: DarkSkyResponse?) -> Unit,
        onError: (throwable: Throwable) -> Unit
    ) = dataSource.getCurrentWeather(
        key = API_KEY,
        latitude = model.latitude,
        longitude = model.longitude,
        units = UNIT_US,
        lang = LANGUAGE
    ).enqueue(object : Callback<DarkSkyResponse> {
        override fun onFailure(call: Call<DarkSkyResponse>, t: Throwable) = onError.invoke(t)

        override fun onResponse(call: Call<DarkSkyResponse>, response: Response<DarkSkyResponse>) =
            onSuccess.invoke(response.body())
    })
}