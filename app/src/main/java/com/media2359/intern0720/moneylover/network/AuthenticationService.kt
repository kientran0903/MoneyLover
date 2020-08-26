package com.media2359.intern0720.moneylover.network


import com.media2359.intern0720.moneylover.entity.AccountEntity
import com.media2359.intern0720.moneylover.entity.request.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/v1.0/login")
    fun login(@Body loginRequest: LoginRequest): Call<AccountEntity>


}