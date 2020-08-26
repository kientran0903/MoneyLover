package com.media2359.intern0720.moneylover.network

import android.util.Log
import com.media2359.intern0720.moneylover.BuildConfig
import okhttp3.OkHttpClient
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
}