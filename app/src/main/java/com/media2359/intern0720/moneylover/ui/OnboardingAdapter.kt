package com.media2359.intern0720.moneylover.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.model.OnboardingItem

class OnboardingAdapter(private val onboardings: List<OnboardingItem>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(onboardings[position])
    }

    override fun getItemCount(): Int {
        return onboardings.size
    }

    inner class OnboardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val imageIcon = view.findViewById<ImageView>(R.id.img_onboarding_icon)

        fun bind(onboardingActivity: OnboardingItem) {
            textTitle.text = onboardingActivity.title
            imageIcon.setImageResource(onboardingActivity.icon)
        }
    }
}