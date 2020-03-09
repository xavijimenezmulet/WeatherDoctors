package com.xjm.weatherdoctors.interactors

import com.xjm.weatherdoctors.data.WeatherRepository
import com.xjm.weatherdoctors.domain.model.DarkSkyRequest
import com.xjm.weatherdoctors.domain.model.DarkSkyResponse

/**
 *   @author xavijimenez
 *   @since 09/03/2020
 *   @version 1.0.0
 */
class GetWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(
        model: DarkSkyRequest,
        onSuccess: (darkSkyResponse: DarkSkyResponse?) -> Unit,
        onError: (throwable: Throwable) -> Unit
    ) = repository.getCurrentWeather(model, onSuccess,onError)

}