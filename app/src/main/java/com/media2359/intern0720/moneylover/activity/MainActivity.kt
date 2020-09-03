package com.media2359.intern0720.moneylover.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.fragment.AccountFragment
import com.media2359.intern0720.moneylover.fragment.TransactionsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val transactionsFragment = TransactionsFragment()
    val accountFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTransactions.isSelected = true
        makeCurrentFragment(transactionsFragment)
//        bottom_navigation.setOnNavigationItemSelectedListener {
//            when (it.itemId){
//                R.id.ic_transactions -> makeCurrentFragment(transactionsFragment)
//                R.id.ic_account -> makeCurrentFragment(accountFragment)
//            }
//            true
//        }
//        btnAddTransactions.setOnClickListener {
//            return@setOnClickListener
//        }

        tvTransactions.setOnClickListener {
            tvTransactions.isSelected = true
            tvAccount.isSelected = false
            makeCurrentFragment(transactionsFragment)
        }

        tvAccount.setOnClickListener {
            tvTransactions.isSelected = false
            tvAccount.isSelected = true
            makeCurrentFragment(accountFragment)
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    //TODO
    // Align tab item to left or center
    // Add Plus button
    // user fulname and toolbar should be center
}