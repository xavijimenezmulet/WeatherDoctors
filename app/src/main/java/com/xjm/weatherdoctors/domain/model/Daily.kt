package com.xjm.weatherdoctors.domain.model

/**
 *   @author xavijimenez
 *   @since 08/03/2020
 *   @version 1.0.0
 */
data class Daily(
    val summary: String?,
    val icon: String?,
    val data: MutableList<Data>
)