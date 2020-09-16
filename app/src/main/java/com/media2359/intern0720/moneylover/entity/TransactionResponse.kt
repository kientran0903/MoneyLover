package com.media2359.intern0720.moneylover.entity

data class TransactionResponse(
    val id: Int,
    val createAt: String,
    val updateAt: String,
    val userId: Int,
    val type: String,
    val category: String,
    val amount: Int,
    val description: String,
    val date: String
)