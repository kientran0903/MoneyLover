package com.media2359.intern0720.moneylover.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.utils.MoneyLoverUtils

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val token = MoneyLoverUtils.moneyLoverManager?.getAccessToken()
            if (token.isNullOrBlank()) {
                startActivity(Intent(this, OnboardingActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, SPLASH_TIME_OUT)
    }
}