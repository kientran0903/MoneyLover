package com.media2359.intern0720.moneylover.entity.request

data class AddTransactionRequest (
    val type : String,
    val category : String,
    val amount : Int,
    val description : String,
    val date : String
)