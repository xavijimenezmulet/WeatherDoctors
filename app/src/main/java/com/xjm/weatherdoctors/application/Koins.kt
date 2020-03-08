package com.xjm.weatherdoctors.application

import com.xjm.weatherdoctors.data.WeatherDataSource
import com.xjm.weatherdoctors.data.WeatherRepository
import com.xjm.weatherdoctors.domain.REST.RESTAdapter
import org.koin.dsl.module

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 *
 *  Koin implementation
 */

// Repositories
val repositoryModule = module(override = true) {
    factory { WeatherRepository(get()) }
}

// View Model
val viewModelModule = module(override = true) {

}

// Use Case
val useCaseModule = module(override = true) {

}

// Data Source
val dataSourceModule = module(override = true) {
    factory { RESTAdapter.createService(WeatherDataSource::class.java) }
}