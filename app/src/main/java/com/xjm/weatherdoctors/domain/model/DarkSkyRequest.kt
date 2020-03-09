package com.xjm.weatherdoctors.domain.model

/**
 *   @author xavijimenez
 *   @since 08/03/2020
 *   @version 1.0.0
 *
 *   This model is used to get the data from the service
 */
data class DarkSkyRequest(

// =====================================================================================================================
// Attributes
// =====================================================================================================================

    val latitude: Double,
    val longitude: Double,
    val timezone: String
)