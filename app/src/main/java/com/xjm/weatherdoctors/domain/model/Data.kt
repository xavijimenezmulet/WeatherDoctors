package com.xjm.weatherdoctors.domain.model

/**
 *   @author xavijimenez
 *   @since 08/03/2020
 *   @version 1.0.0
 */
data class Data(

// =====================================================================================================================
// Attributes
// =====================================================================================================================

    val time: Long,
    val summary: String? = "",
    val icon: String? = "",
    val nearestStormDistance: Int?,
    val nearestStormBearing: Int?,
    val precipIntensity: Double?,
    val precipProbability: Double?,
    val temperature: Double?,
    val apparentTemperature: Double?,
    val apparentTemperatureHigh: Double?,
    val apparentTemperatureLow: Double?,
    val apparentTemperatureHighTime: Long?,
    val apparentTemperatureLowTime: Long?,
    val dewPoint: Double?,
    val humidity: Double?,
    val pressure: Double?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windGustTime: Long?,
    val windBearing: Int?,
    val cloudCover: Double?,
    val uvIndex: Int?,
    val uvIndexTime: Long?,
    val visibility: Double?,
    val ozone: Double?
)