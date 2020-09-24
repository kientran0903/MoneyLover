package com.media2359.intern0720.moneylover.entity

import com.google.gson.annotations.SerializedName

data class AccountEntity(
    val user: UserEntity,
    val token: TokenEntity
)

data class UserEntity(
    val id: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updateAt") val updateAt: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("role") val role: String,
    @SerializedName("email") val email: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("phone") val phone: Int,
    @SerializedName("balance") val balance: Int
)

data class TokenEntity(
    @SerializedName("expiresIn") val expiresIn: Int,
    @SerializedName("accessToken") val accessToken: String
)