package com.xjm.weatherdoctors.presentation.dashboard.ui.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xjm.weatherdoctors.domain.model.DarkSkyRequest
import com.xjm.weatherdoctors.domain.model.DarkSkyResponse
import com.xjm.weatherdoctors.interactors.usecase.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 *
 *   This View Model is used to help the view [DashboardActivity] fill the data and check location permission
 */
class DashboardViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

// =====================================================================================================================
// View Model methods
// =====================================================================================================================

    /**
     * Used to get the current weather from current location. It will return [DarkSkyResponse] that will help to
     * fill the data on the view
     *
     * @param latitude the current latitude
     * @param longitude the current longitude
     */
    fun getWeather(latitude: Double, longitude: Double): LiveData<DarkSkyResponse?> {

        val liveData = MutableLiveData<DarkSkyResponse?>()

        runBlocking(Dispatchers.IO) {
            getWeatherUseCase.invoke(
                DarkSkyRequest(latitude, longitude, "Europe/Madrid"),
                { liveData.postValue(it) },
                {
                    it.printStackTrace()
                    liveData.postValue(null)
                }
            )
        }

        return liveData
    }

    /**
     * Called to che the user location permission
     * @param context the current context
     */
    fun checkUserLocationPermission(context: Context): LiveData<Boolean> {

        val liveData = MutableLiveData<Boolean>()

        liveData.value =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        return liveData
    }
}