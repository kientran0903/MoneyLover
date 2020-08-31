package com.media2359.intern0720.moneylover.entity.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)