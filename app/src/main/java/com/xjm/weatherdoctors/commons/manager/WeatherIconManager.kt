package com.xjm.weatherdoctors.commons.manager

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.xjm.weatherdoctors.R

/**
 *   @author xavijimenez
 *   @since 08/03/2020
 *   @version 1.0.0
 *
 *   This manager is used to manage the icon data from service because the service returns a string that will be
 *   a key to get the weather icon
 */
class WeatherIconManager(private val context: Context) {

// =====================================================================================================================
// Icon methods
// =====================================================================================================================
    /**
     * Use this method to get the weather icon
     * @param iconKey the icon (from service)
     */
    fun getIcon(iconKey: String?): Drawable? = map(context)[iconKey]

    /**
     * Since the Dark Sky API response icon doesn't directly map to our drawables, we have to do manually. This map help
     * to get the current weather drawable.
     */
    private fun map(context: Context): Map<String, Drawable> {
        val weatherIconMap = HashMap<String, Drawable>()
        weatherIconMap["clear-day"] = ContextCompat.getDrawable(context, R.drawable.ic_weather_clear_day)!!
        weatherIconMap["clear-night"] = ContextCompat.getDrawable(context, R.drawable.ic_weather_clear_night)!!
        weatherIconMap["rain"] = ContextCompat.getDrawable(context, R.drawable.ic_weather_rain)!!
        weatherIconMap["snow"] = ContextCompat.getDrawable(context, R.drawable.ic_weather_snow)!!
        weatherIconMap["sleet"] = ContextCompat.getDrawable(context, R.drawable.ic_weather_sleet)!!
        weatherIconMap["wind"] = ContextCompat.getDrawable(context, R.drawable.ic_weather_wind)!!
        weatherIconMap["fog"] = ContextCompat.getDrawable(context, R.drawable.ic_weather_fog)!!
        weatherIconMap["cloudy"] = ContextCompat.getDrawable(context, R.drawable.ic_weather_cloudy)!!
        weatherIconMap["partly-cloudy-day"] =
            ContextCompat.getDrawable(context, R.drawable.ic_weather_partly_cloudy_day)!!
        weatherIconMap["partly-cloudy-night"] =
            ContextCompat.getDrawable(context, R.drawable.ic_weather_party_cloudy_night)!!
        return weatherIconMap
    }

}