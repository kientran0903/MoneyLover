package com.media2359.intern0720.moneylover

import android.app.Application
import com.media2359.intern0720.moneylover.utils.MoneyLoverUtils

class MoneyLoverApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        MoneyLoverUtils.createAuthenticationService()
        MoneyLoverUtils.createTransactionService()
        MoneyLoverUtils.createMoneyLoverManager(this)
    }
}