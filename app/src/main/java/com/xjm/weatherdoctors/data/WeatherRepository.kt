package com.xjm.weatherdoctors.data

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */
class WeatherRepository(private val dataSource: WeatherDataSource) {

    val key = "e93394f89420bac47cb3274632d77123"

    suspend fun getCurrentWeather(){}
}