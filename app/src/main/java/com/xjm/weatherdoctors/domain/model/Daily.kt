package com.xjm.weatherdoctors.domain.model

/**
 *   @author xavijimenez
 *   @since 08/03/2020
 *   @version 1.0.0
 *
 *   This model is used to get the daily data and the week weather
 */
data class Daily(

// =====================================================================================================================
// Attributes
// =====================================================================================================================

    val summary: String?,
    val icon: String?,
    val data: MutableList<Data>
)