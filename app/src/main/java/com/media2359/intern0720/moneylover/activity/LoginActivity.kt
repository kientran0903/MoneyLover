package com.media2359.intern0720.moneylover.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.entity.AccountEntity
import com.media2359.intern0720.moneylover.entity.request.LoginRequest
import com.media2359.intern0720.moneylover.network.AuthenticationService
import com.media2359.intern0720.moneylover.network.MoneyLoverNetwork
import com.media2359.intern0720.moneylover.utils.showToastMessage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val authenticationService =
            MoneyLoverNetwork.createService(AuthenticationService::class.java)

        btnBack.setOnClickListener {
            Intent(applicationContext, OnboardingActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
        btnSignIn.setOnClickListener {
            if (etEmail.text.toString().isEmpty()|| etPassword.text.toString().isEmpty()){
                return@setOnClickListener
            }
            val request = authenticationService.login(
                loginRequest = LoginRequest(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            )
            request.enqueue(object : Callback<AccountEntity> {
                override fun onResponse(
                    call: Call<AccountEntity>,
                    response: Response<AccountEntity>
                ) {
                    if (response.isSuccessful) {
                        Intent(applicationContext, MainActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<AccountEntity>, t: Throwable) {
                    showToastMessage(t.message)
                }
            })

        }
        btnRegister.setOnClickListener {
            Intent(applicationContext, SignupActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}