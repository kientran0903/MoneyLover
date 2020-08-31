package com.media2359.intern0720.moneylover.network

import android.util.Log
import com.google.gson.Gson
import com.media2359.intern0720.moneylover.BuildConfig
import com.media2359.intern0720.moneylover.entity.ErrorEntity
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoneyLoverNetwork {
    var client: OkHttpClient? = null
    var retrofit: Retrofit? = null
    fun <T> createService(service: Class<T>): T {
        if (retrofit == null) {
            val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    if (BuildConfig.DEBUG) {
                        Log.d("MoneyLover_", message)
                    }
                }
            })
            logger.level = HttpLoggingInterceptor.Level.BODY
            client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl("https://msi.center/2359/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit?.create(service)!!
    }

    fun parseErrorBody(responseBody: ResponseBody?): ErrorEntity {
        val errorEntity: ErrorEntity= Gson().fromJson(responseBody?.string(), ErrorEntity::class.java)
        return errorEntity
    }
}