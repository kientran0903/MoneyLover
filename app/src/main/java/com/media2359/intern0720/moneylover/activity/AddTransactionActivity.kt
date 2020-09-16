package com.media2359.intern0720.moneylover.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.activity.SelectCategoryActivity.Companion.KEY_CATEGORY_NAME
import com.media2359.intern0720.moneylover.activity.SelectCategoryActivity.Companion.KEY_CATEGORY_TYPE
import com.media2359.intern0720.moneylover.entity.AccountEntity
import com.media2359.intern0720.moneylover.entity.TransactionResponse
import com.media2359.intern0720.moneylover.entity.request.AddTransactionRequest
import com.media2359.intern0720.moneylover.network.MoneyLoverNetwork
import com.media2359.intern0720.moneylover.utils.MoneyLoverManager
import com.media2359.intern0720.moneylover.utils.MoneyLoverUtils
import com.media2359.intern0720.moneylover.utils.showToastMessage
import kotlinx.android.synthetic.main.add_transaction.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class AddTransactionActivity : AppCompatActivity() {
    private val SECOND_ACTIVITY_REQUEST_CODE = 1000
    lateinit var type : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_transaction)

        btnCancel.setOnClickListener {
            Intent(applicationContext, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        btnSave.setOnClickListener {
            if (etAmount.text.toString().isEmpty()&&btnSelectCategory.isEmpty()){
//                btnSave.setTextColor(R.color.Save_unselected)
                return@setOnClickListener
            }else {
                val requestCreateTransaction =
                    MoneyLoverUtils.moneyLoverManager?.getAccessToken()?.let { it1 ->
                        MoneyLoverUtils.transactionService?.createTransaction(
                            createTransaction = AddTransactionRequest(
                                type = type,
                                category = tvSelectCategory.text.toString(),
                                amount = etAmount.text.toString().toInt(),
                                description = etNote.text.toString(),
                                date = "2020-08-20T07:00:00.00Z"
                            ),
                            token = it1
                        )
                    }
                requestCreateTransaction?.enqueue(object : Callback<TransactionResponse> {
                    override fun onResponse(
                        call: Call<TransactionResponse>,
                        response: Response<TransactionResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.i("TAG", "onResponse: ") showToastMessage ("Add Transaction Success")
                            finish()
                        } else {
                            val errorEntity =
                                MoneyLoverNetwork.parseErrorBody(responseBody = response.errorBody())
                            showToastMessage(errorEntity.message)
                        }
                    }

                    override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                        showToastMessage(t.message)
                    }
                })
            }
        }

        btnSelectCategory.setOnClickListener{
            val intent = Intent(this, SelectCategoryActivity::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }

        val textView = findViewById<TextView>(R.id.tvDate)
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy")
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        btnDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    textView.text = sdf.format(calendar.time)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        tvDate.text = sdf.format(calendar.time)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val returnString = data!!.getStringExtra(KEY_CATEGORY_NAME)
                tvSelectCategory.setText("" + returnString)
                type = data!!.getStringExtra(KEY_CATEGORY_TYPE)

            }
        }
    }
}