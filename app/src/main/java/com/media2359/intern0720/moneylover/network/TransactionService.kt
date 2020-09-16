package com.media2359.intern0720.moneylover.network

import com.media2359.intern0720.moneylover.entity.TransactionResponse
import com.media2359.intern0720.moneylover.entity.request.AddTransactionRequest
import com.media2359.intern0720.moneylover.utils.MoneyLoverManager
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TransactionService {
    @POST("transactions/v1.0")
    fun createTransaction(@Body createTransaction: AddTransactionRequest, @Header ("Authorization") token : String ) : Call<TransactionResponse>
}