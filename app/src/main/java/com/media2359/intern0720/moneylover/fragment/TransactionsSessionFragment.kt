package com.media2359.intern0720.moneylover.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.ui.TransactionListAdapter
import kotlinx.android.synthetic.main.fragment_transactions_session.*

class TransactionsSessionFragment : Fragment() {
    val adapter = TransactionListAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transactions_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerListTransaction.layoutManager = LinearLayoutManager(context)
        recyclerListTransaction.setHasFixedSize(true)
        recyclerListTransaction.adapter = adapter
    }
}