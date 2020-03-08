package com.xjm.weatherdoctors.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */
interface WeatherDataSource {

    @GET("forecast/{key}/{latitude},{longitude}")
    fun getCurrentWeather(
        @Path("key") key: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Query("units") units: String,
        @Query("exclude") exclude: String
    )

}