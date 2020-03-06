package com.xjm.weatherdoctors.presentation.splash

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.xjm.weatherdoctors.R
import com.xjm.weatherdoctors.presentation.dashboard.DashboardActivity

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 *
 *   This activity is used to initialize the application, it will help as well to launch the [DashboardActivity].
 *   Also is prepared to get data while the animation is loading.
 */
class SplashActivity : AppCompatActivity() {

    var splashLottie: LottieAnimationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        bindViews()
        configAnimation()
    }

    /**
     * Called to bind views
     */
    private fun bindViews() {
        splashLottie = findViewById(R.id.splash_lottie)
    }

    /**
     * Called to config the [LottieAnimationView]
     */
    private fun configAnimation() {
        splashLottie?.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                //Nothing to do
            }

            override fun onAnimationEnd(animation: Animator?) {
                navigateToDashboard()
            }

            override fun onAnimationCancel(animation: Animator?) {
                //Nothing to do
            }

            override fun onAnimationStart(animation: Animator?) {
                //Nothing to do
            }
        })
    }

    /**
     * Called to navigate to the [DashboardActivity] after animation finishes
     */
    private fun navigateToDashboard() {
        startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
        overridePendingTransition(R.xml.fade_in, R.xml.fade_out)
    }
}
