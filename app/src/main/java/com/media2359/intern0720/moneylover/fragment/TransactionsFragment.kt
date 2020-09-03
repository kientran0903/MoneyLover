package com.media2359.intern0720.moneylover.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.media2359.intern0720.moneylover.R

class TransactionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    //TODO
    // Toolbar: user icon, name, 3 dot
    // Apply 3 dot menu
}