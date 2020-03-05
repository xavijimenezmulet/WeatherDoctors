package com.xjm.weatherdoctors.application

import com.xjm.weatherdoctors.data.WeatherDataSource
import com.xjm.weatherdoctors.data.WeatherRepository
import com.xjm.weatherdoctors.domain.RESTAdapter
import org.koin.dsl.module

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */

// Repositories
val repositoryModule = module(override = true) {
    factory { WeatherRepository(get()) }
}

val viewModelModule = module(override = true) {

}

// Repositories
val useCaseModule = module(override = true) {

}

// Repositories
val dataSourceModule = module(override = true) {
    factory { RESTAdapter.createService(WeatherDataSource::class.java) }
}