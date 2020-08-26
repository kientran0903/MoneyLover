package com.media2359.intern0720.moneylover.entity.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String
)
