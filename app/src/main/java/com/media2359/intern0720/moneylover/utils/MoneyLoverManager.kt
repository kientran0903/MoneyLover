package com.media2359.intern0720.moneylover.utils

import android.content.Context
import android.content.SharedPreferences

class MoneyLoverManager(context: Context) {
    private val PREFERENCE_FILE_KEY = "PREFERENCE_FILE_KEY"
    private val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"

    private var sharePref: SharedPreferences? = null

    init {
        sharePref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
    }

    fun getAccessToken(): String {
        return sharePref?.getString(ACCESS_TOKEN_KEY,"") ?: ""
    }

    fun setAccessToken(token: String) {
        val editor: SharedPreferences.Editor = sharePref?.edit() ?: return
        editor.putString(ACCESS_TOKEN_KEY, "Bearer $token")
        editor.apply()
    }
}