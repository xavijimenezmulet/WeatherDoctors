package com.xjm.weatherdoctors.application

import com.xjm.weatherdoctors.commons.utils.WeatherIconUtils
import com.xjm.weatherdoctors.data.WeatherDataSource
import com.xjm.weatherdoctors.data.WeatherRepository
import com.xjm.weatherdoctors.domain.REST.RESTAdapter
import com.xjm.weatherdoctors.interactors.GetWeatherUseCase
import com.xjm.weatherdoctors.presentation.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 *
 *  Koin implementation
 */

// =====================================================================================================================
// Repositories
// =====================================================================================================================

val repositoryModule = module(override = true) {
    factory { WeatherRepository(get()) }
}

// =====================================================================================================================
// View Model
// =====================================================================================================================

val viewModelModule = module(override = true) {
    viewModel { DashboardViewModel(get()) }
}

// =====================================================================================================================
// Use Case
// =====================================================================================================================

val useCaseModule = module(override = true) {
    factory { GetWeatherUseCase(get()) }
}

// =====================================================================================================================
// Data Source
// =====================================================================================================================

val dataSourceModule = module(override = true) {
    single { RESTAdapter.createService(WeatherDataSource::class.java) }
}

// =====================================================================================================================
// Helpers
// =====================================================================================================================

val utilsModule = module(override = true) {
    single { WeatherIconUtils(get()) }
}