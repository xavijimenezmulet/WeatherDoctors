package com.xjm.weatherdoctors.presentation.dashboard

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.xjm.weatherdoctors.R
import com.xjm.weatherdoctors.commons.utils.WeatherIconUtils
import com.xjm.weatherdoctors.domain.model.Data
import kotlinx.android.synthetic.main.activity_main.view.*
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.*

/**
 *   @author xavijimenez
 *   @since 09/03/2020
 *   @version 1.0.0
 */
class DashboardWeatherAdapter(
    private var mDataSet: List<Data>,
    private val iconUtils: WeatherIconUtils
): RecyclerView.Adapter<DashboardWeatherAdapter.ViewHolder>() {

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
            "Today"
        } else {
            val date = Date(mDataSet[position].time * 1000)
            val dateFormatter = SimpleDateFormat("EEEE", Locale.US)
            dateFormatter.format(date)
        }

        with(mDataSet[position]) {
            holder.title.text = day
            holder.imageView.setImageDrawable(iconUtils.getIcon(icon))
            holder.description.text = summary
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
    }
}