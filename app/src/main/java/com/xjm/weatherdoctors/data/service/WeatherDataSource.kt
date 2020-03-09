package com.xjm.weatherdoctors.data.service

import com.xjm.weatherdoctors.domain.model.Daily
import com.xjm.weatherdoctors.domain.model.DarkSkyRequest
import com.xjm.weatherdoctors.domain.model.DarkSkyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */
interface WeatherDataSource {

// =====================================================================================================================
// Api methods
// =====================================================================================================================

    @GET("forecast/{key}/{latitude},{longitude}")
    fun getCurrentWeather(
        @Path("key") key: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Query("units") units: String,
        @Query("lang") lang: String
    ): Call<DarkSkyResponse>

}