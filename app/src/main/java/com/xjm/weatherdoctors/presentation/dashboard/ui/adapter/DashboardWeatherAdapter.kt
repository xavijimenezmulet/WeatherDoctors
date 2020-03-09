package com.xjm.weatherdoctors.presentation.dashboard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.xjm.weatherdoctors.R
import com.xjm.weatherdoctors.commons.manager.WeatherIconManager
import com.xjm.weatherdoctors.domain.model.Data
import java.text.SimpleDateFormat
import java.util.*

/**
 *   @author xavijimenez
 *   @since 09/03/2020
 *   @version 1.0.0
 *
 *   This adapter is used to fill the data from [com.xjm.weatherdoctors.presentation.dashboard.ui.view.DashboardActivity]
 *   and get the list of week weather.
 */
class DashboardWeatherAdapter(
    private var mDataSet: List<Data>,
    private val iconManager: WeatherIconManager
) : RecyclerView.Adapter<DashboardWeatherAdapter.ViewHolder>() {

// =====================================================================================================================
// Attributes
// =====================================================================================================================

    companion object {
        const val PATTERN = "EEEE"
        const val REGION = "es"
        const val COUNTRY = "ES"
        const val TODAY = "Hoy"
    }

// =====================================================================================================================
// Config
// =====================================================================================================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_day_card, parent, false).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        })


    override fun getItemCount(): Int = mDataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val day = if (position == 0) {
            TODAY
        } else {
            val date = Date(mDataSet[position].time * 1000)
            val dateFormatter = SimpleDateFormat(PATTERN, Locale(REGION, COUNTRY))
            dateFormatter.format(date).capitalize()
        }

        with(mDataSet[position]) {
            holder.title.text = day
            holder.imageView.setImageDrawable(iconManager.getIcon(icon))
            holder.description.text = summary
        }
    }

// =====================================================================================================================
// View Holder
// =====================================================================================================================

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
    }
}