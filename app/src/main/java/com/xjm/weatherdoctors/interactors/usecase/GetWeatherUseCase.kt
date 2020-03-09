package com.xjm.weatherdoctors.interactors.usecase

import com.xjm.weatherdoctors.data.service.WeatherRepository
import com.xjm.weatherdoctors.domain.model.DarkSkyRequest
import com.xjm.weatherdoctors.domain.model.DarkSkyResponse

/**
 *   @author xavijimenez
 *   @since 09/03/2020
 *   @version 1.0.0
 *
 *   This use case is used to invoke the current weather
 */
class GetWeatherUseCase(private val repository: WeatherRepository) {

// =====================================================================================================================
// Use case methods
// =====================================================================================================================

    /**
     * Called to get from the service the curren weather
     */
    suspend operator fun invoke(
        model: DarkSkyRequest,
        onSuccess: (darkSkyResponse: DarkSkyResponse?) -> Unit,
        onError: (throwable: Throwable) -> Unit
    ) = repository.getCurrentWeather(model, onSuccess, onError)

}