package com.media2359.intern0720.moneylover.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.media2359.intern0720.moneylover.R

fun Context.showToastMessage(mes: String?) {
    Toast.makeText(this, mes ?: getString(R.string.unknown_error), Toast.LENGTH_LONG).show()
}

fun Context.showToastMessage(@StringRes mes: Int) {
    Toast.makeText(this, getString(mes), Toast.LENGTH_LONG).show()
}

