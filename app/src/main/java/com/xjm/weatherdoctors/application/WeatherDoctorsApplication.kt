package com.xjm.weatherdoctors.application

import android.app.Application
import com.xjm.weatherdoctors.application.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */
class WeatherDoctorsApplication : Application() {

// =====================================================================================================================
// Config && Koin
// =====================================================================================================================

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherDoctorsApplication)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    useCaseModule,
                    dataSourceModule,
                    utilsModule
                )
            )
        }
    }
}