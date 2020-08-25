package com.media2359.intern0720.moneylover

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_onboarding.*


class OnboardingActivity : AppCompatActivity() {

    private val onboardingAdapter = OnboardingAdapter(
        listOf(
            Onboarding(
                "Manage money efficiently with Money Lover",
                R.drawable.deposit_1
            ),
            Onboarding(
                "Manage your personal finance and achieve your financial goals",
                R.drawable.pie_chart_1
            ),
            Onboarding(
                "Keep track of every penny with in-depth reports",
                R.drawable.money_1
            ),
            Onboarding(
                "Notify you to input your transaction daily",
                R.drawable.bell_1
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        onboardingViewPager.adapter = onboardingAdapter
        setupIndicators()
        setCurrentIndicator(0)
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        btn_New_to_money_lover.setOnClickListener {
            Intent(applicationContext, SignupActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
        btn_Already_have_account.setOnClickListener {
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onboardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive)
                )
            }
        }
    }
}