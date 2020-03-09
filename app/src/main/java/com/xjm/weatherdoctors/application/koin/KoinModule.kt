package com.xjm.weatherdoctors.application.koin

import com.xjm.weatherdoctors.commons.manager.WeatherIconManager
import com.xjm.weatherdoctors.data.service.WeatherDataSource
import com.xjm.weatherdoctors.data.service.WeatherRepository
import com.xjm.weatherdoctors.domain.REST.RESTAdapter
import com.xjm.weatherdoctors.interactors.usecase.GetWeatherUseCase
import com.xjm.weatherdoctors.presentation.dashboard.ui.viewmodel.DashboardViewModel
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
    single { WeatherIconManager(get()) }
}