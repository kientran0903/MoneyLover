package com.media2359.intern0720.moneylover.network

import com.media2359.intern0720.moneylover.entity.TransactionResponse
import com.media2359.intern0720.moneylover.entity.request.AddTransactionRequest
import retrofit2.Call
import retrofit2.http.*

interface TransactionService {

    @GET("/transactions/v1.0")
    fun getTransactionByDate(
        @Header("Authorization") token: String,
        @Query("dateFrom", encoded = true) dateFrom: String,
        @Query("dateTo", encoded = true) dateTo: String,
    ): Call<List<TransactionResponse>>

    @POST("transactions/v1.0")
    fun createTransaction(
        @Header("Authorization") token: String,
        @Body createTransaction: AddTransactionRequest,
    ): Call<TransactionResponse>
}