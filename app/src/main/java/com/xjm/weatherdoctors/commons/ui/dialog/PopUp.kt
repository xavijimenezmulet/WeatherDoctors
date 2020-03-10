package com.xjm.weatherdoctors.commons.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.xjm.weatherdoctors.R

/**
 *   @author xavijimenez
 *   @since 10/03/2020
 *   @version 1.0.0
 *
 *   Use this class to show the pop ups of app
 */
object PopUp {

// =====================================================================================================================
// Pop Up methods
// =====================================================================================================================

    /**
     * Called to show a dialog error from service
     * @param context the current context
     */
    fun showDialogError(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.alert_dialog_title))
        builder.setMessage(context.getString(R.string.alert_dialog_msg))
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            //Nothing to do
        }

        builder.show()
    }

    /**
     * Called to show a dialog exit to finish all activities
     * @param activity the current activity
     */
    fun showExit(activity: AppCompatActivity) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(activity.getString(R.string.alert_dialog_title_exit))
        builder.setMessage(activity.getString(R.string.alert_dialog_msg_exit))
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            activity.finishAffinity()
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            //Nothing to do
        }

        builder.show()
    }
}