package com.xjm.weatherdoctors.domain.model

/**
 *   @author xavijimenez
 *   @since 09/03/2020
 *   @version 1.0.0
 */
class DarkSkyResponse(

// =====================================================================================================================
// Attributes
// =====================================================================================================================

    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val currently: Data,
    val daily: Daily
)