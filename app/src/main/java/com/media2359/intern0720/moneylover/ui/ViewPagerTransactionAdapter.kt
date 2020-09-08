package com.media2359.intern0720.moneylover.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.media2359.intern0720.moneylover.fragment.TransactionsSessionFragment

class ViewPagerTransactionAdapter(fragmentActivity: FragmentActivity,internal var tabs: Int) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return tabs
    }

    override fun createFragment(position: Int): Fragment {
        return TransactionsSessionFragment()
    }

}


