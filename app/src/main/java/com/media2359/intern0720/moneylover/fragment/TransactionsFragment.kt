package com.media2359.intern0720.moneylover.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.ui.ViewPagerTransactionAdapter
import kotlinx.android.synthetic.main.fragment_transactions.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TransactionsFragment : Fragment() {
    val MONTHS = 6
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerTransaction.adapter = ViewPagerTransactionAdapter(activity!!, MONTHS)
        TabLayoutMediator(
            tabLayout, viewPagerTransaction
        ) { tab, position ->
            run {
                val delta = MONTHS - position
                val date = Date()
                date.month = date.month - delta
                val df: DateFormat = SimpleDateFormat("MM/yyyy")
                tab.text = df.format(date)
//                tab.text = "${if (date.month <= 9) "0" else ""}$dateTmp/${date.year}"

            }
        }.attach()


    }

    //TODO
    // Toolbar: user icon, name, 3 dot
    // Apply 3 dot menu
}