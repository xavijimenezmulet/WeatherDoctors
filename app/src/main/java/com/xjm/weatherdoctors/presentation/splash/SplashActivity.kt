package com.xjm.weatherdoctors.presentation.splash

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.xjm.weatherdoctors.R
import com.xjm.weatherdoctors.presentation.dashboard.DashboardActivity


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

    private fun configAnimation() {
        splashLottie?.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                //Nothing to do
            }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                overridePendingTransition(R.xml.fade_in, R.xml.fade_out)
            }

            override fun onAnimationCancel(animation: Animator?) {
                //Nothing to do
            }

            override fun onAnimationStart(animation: Animator?) {
                //Nothing to do
            }
        })
    }
}
