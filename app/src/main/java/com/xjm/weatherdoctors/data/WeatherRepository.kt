package com.xjm.weatherdoctors.data

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */
class WeatherRepository(private val dataSource: WeatherDataSource) {

    suspend fun getCurrentWeather(){}
}