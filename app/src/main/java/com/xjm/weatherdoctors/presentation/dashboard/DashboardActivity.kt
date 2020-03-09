package com.xjm.weatherdoctors.presentation.dashboard

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xjm.weatherdoctors.R
import com.xjm.weatherdoctors.commons.utils.WeatherIconUtils
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

    private val iconUtils: WeatherIconUtils by inject()
    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //show loader
        loadData()
    }

    private fun loadData() {

        viewModel.checkUserLocationPermission(this).observe(this, Observer { hasPermissions ->
            if (hasPermissions) {
                viewModel.getWeather().observe(this@DashboardActivity, Observer { daily ->
                    if (daily == null) {
                        //TODO(Show popup bitch)
                    }
                    else {
                        with(recyclerView) {
                            layoutManager = LinearLayoutManager(
                                this@DashboardActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            adapter = DashboardWeatherAdapter(daily.daily.data, iconUtils)
                        }
                    }
                    //hideLoader
                })
            } else {
                requestPermissions()
            }
        })
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 0 && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Timber.v("User gave location permission, continue with getting user's last location.")
            loadData()
        } else {
            Timber.v("User refused to give location permission. Continue using the default location.")
        }
    }

}
