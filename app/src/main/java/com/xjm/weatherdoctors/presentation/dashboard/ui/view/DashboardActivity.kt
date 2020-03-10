package com.xjm.weatherdoctors.presentation.dashboard.ui.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.xjm.weatherdoctors.R
import com.xjm.weatherdoctors.commons.manager.WeatherIconManager
import com.xjm.weatherdoctors.commons.ui.dialog.PopUp
import com.xjm.weatherdoctors.domain.model.DarkSkyResponse
import com.xjm.weatherdoctors.presentation.dashboard.ui.viewmodel.DashboardViewModel
import com.xjm.weatherdoctors.presentation.dashboard.ui.adapter.DashboardWeatherAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */
class DashboardActivity : AppCompatActivity() {

// =====================================================================================================================
// Singletons
// =====================================================================================================================

    private val iconManager: WeatherIconManager by inject()

    // =====================================================================================================================
// View Model
// =====================================================================================================================
    private val viewModel: DashboardViewModel by viewModel()

// =====================================================================================================================
// Attributes
// =====================================================================================================================

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude: Double = .0
    private var longitude: Double = .0

// =====================================================================================================================
// Config
// =====================================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

    }

// =====================================================================================================================
// Private methods
// =====================================================================================================================

    /**
     * Called to start the location services, show loader and load data
     */
    private fun initData() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        appLoader?.showLoader()
        loadData()
    }

    /**
     * Called to load data:
     * - Check if the user has location permissions
     * - Check the current location from the user
     * - Call the weather service and manage the errors
     */
    private fun loadData() {
        viewModel.checkUserLocationPermission(this).observe(this, Observer { hasPermissions ->
            if (hasPermissions) {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.
                        latitude = location?.latitude ?: .0
                        longitude = location?.longitude ?: .0

                        viewModel.getWeather(latitude, longitude).observe(this@DashboardActivity, Observer { daily ->
                            if (daily == null) {
                                PopUp.showDialogError(this)
                            } else {
                                with(recyclerView) {
                                    layoutManager = LinearLayoutManager(
                                        this@DashboardActivity,
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                    setData(daily)
                                    adapter =
                                        DashboardWeatherAdapter(
                                            daily.daily.data,
                                            iconManager
                                        )
                                }
                            }
                            appLoader?.hideLoader(true)
                        })
                    }
            } else {
                requestPermissions()
            }
        })
    }

    /**
     * Called to set the data to the view
     * @param darkSkyResponse the model response (from service data)
     */
    private fun setData(darkSkyResponse: DarkSkyResponse) {
        val temperature: Double? = ((darkSkyResponse.currently.temperature!! - 32) * 5) / 9

        titleTextView.text = darkSkyResponse.timezone
        weatherIcon.setImageDrawable(iconManager.getIcon(darkSkyResponse.currently.icon))
        summaryTextView.text = darkSkyResponse.currently.summary
        temperatureTextView.text = String.format("%.0f°", temperature)
        precipTextView.text = String.format("%.0f%%", darkSkyResponse.currently.precipProbability?.times(100))
        windTextView.text = String.format("%.0f mph", darkSkyResponse.currently.windSpeed)
        weatherContainer.visibility = View.VISIBLE
    }

    /**
     * Called to request the permissions
     * - [Manifest.permission.ACCESS_COARSE_LOCATION]
     * - [Manifest.permission.ACCESS_FINE_LOCATION]
     */
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 0
        )
    }

// =====================================================================================================================
// Permission result
// =====================================================================================================================

    /**
     * The reques permissions result
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 0 && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            Timber.v("User gave location permission, continue with getting user's last location.")
            loadData()
        } else {
            Timber.v("User refused to give location permission. Continue using the default location.")
        }
    }

}
