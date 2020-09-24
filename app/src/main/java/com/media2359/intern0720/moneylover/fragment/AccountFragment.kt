package com.media2359.intern0720.moneylover.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.activity.LoginActivity
import com.media2359.intern0720.moneylover.utils.MoneyLoverUtils
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogOut.setOnClickListener {
            activity?.let {
                MoneyLoverUtils.moneyLoverManager?.setAccessToken("")
                val intent = Intent (it, LoginActivity::class.java)
                it.startActivity(intent)
                activity?.finish()
            }
        }
    }
}