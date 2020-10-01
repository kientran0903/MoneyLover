package com.media2359.intern0720.moneylover.network

import com.media2359.intern0720.moneylover.entity.TransactionResponse
import com.media2359.intern0720.moneylover.entity.request.AddTransactionRequest
import retrofit2.Call
import retrofit2.http.*

interface TransactionService {
    @POST("transactions/v1.0")
    fun createTransaction(
        @Header("Authorization") token: String,
        @Body createTransaction: AddTransactionRequest,
    ): Call<TransactionResponse>

    @GET("/transactions/v1.0")
    fun getTransactionByDate(
        @Header("Authorization") token: String,
        @Path("dateFrom") dateFrom: String,
        @Path("dateTo") dateTo: String
    ): Call<List<TransactionResponse>>
}