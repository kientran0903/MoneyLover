package com.media2359.intern0720.moneylover.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.entity.AccountEntity
import com.media2359.intern0720.moneylover.entity.ErrorEntity
import com.media2359.intern0720.moneylover.entity.request.RegisterRequest
import com.media2359.intern0720.moneylover.network.AuthenticationService
import com.media2359.intern0720.moneylover.network.MoneyLoverNetwork
import com.media2359.intern0720.moneylover.utils.MoneyLoverUtils
import com.media2359.intern0720.moneylover.utils.showToastMessage
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        btnBack1.setOnClickListener {
            Intent(applicationContext, OnboardingActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        btnSignUp.setOnClickListener {
            if (etFirstName.text.toString().isEmpty() || etLastName.text.toString().isEmpty()
                || etEmailSignup.text.toString().isEmpty() || etPasswordSignup.text.toString()
                    .isEmpty() || etConfirmPassword.text.toString().isEmpty()
            ) {
                return@setOnClickListener
            }
            if (etPasswordSignup.text.toString() == etConfirmPassword.text.toString()) {
                val requestSignup = MoneyLoverUtils.authenticationService?.register(
                    register = RegisterRequest(
                        firstName = etFirstName.text.toString(),
                        lastName = etLastName.text.toString(),
                        email = etEmailSignup.text.toString(),
                        password = etPasswordSignup.text.toString()
                    )
                )
                requestSignup?.enqueue(object : Callback<AccountEntity> {
                    override fun onResponse(
                        call: Call<AccountEntity>,
                        response: Response<AccountEntity>
                    ) {
                        if (response.isSuccessful) {
                            Intent(applicationContext, LoginActivity::class.java).also {
                                startActivity(it)
                                finish()
                            }
                        } else {
                            val errorEntity =
                                MoneyLoverNetwork.parseErrorBody(responseBody = response.errorBody())
                            showToastMessage(errorEntity.message)
                        }
                    }

                    override fun onFailure(call: Call<AccountEntity>, t: Throwable) {
                        showToastMessage(t.message)
                    }
                })
            } else {
                showToastMessage(R.string.error_sign_up_confirm_password)
            }
        }

        btnAlreadyHaveAccount.setOnClickListener {
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}