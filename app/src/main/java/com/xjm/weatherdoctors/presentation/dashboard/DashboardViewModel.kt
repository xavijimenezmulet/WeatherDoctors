package com.xjm.weatherdoctors.presentation.dashboard

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xjm.weatherdoctors.domain.model.Daily
import com.xjm.weatherdoctors.domain.model.DarkSkyRequest
import com.xjm.weatherdoctors.domain.model.DarkSkyResponse
import com.xjm.weatherdoctors.interactors.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */
class DashboardViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    fun getWeather(): LiveData<DarkSkyResponse?> {

        val liveData = MutableLiveData<DarkSkyResponse?>()

        runBlocking(Dispatchers.IO) {
            getWeatherUseCase.invoke(
                DarkSkyRequest(42.3601, -71.0589, ""),
                { liveData.postValue(it) },
                {
                    it.printStackTrace()
                    liveData.postValue(null)
                }
            )
        }

        return liveData
    }

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