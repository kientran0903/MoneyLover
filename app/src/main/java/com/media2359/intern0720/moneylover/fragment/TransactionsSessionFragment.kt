package com.media2359.intern0720.moneylover.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.TransactionModel
import com.media2359.intern0720.moneylover.entity.TransactionResponse
import com.media2359.intern0720.moneylover.model.TransactionHeader
import com.media2359.intern0720.moneylover.model.TransactionItem
import com.media2359.intern0720.moneylover.model.TransactionSubHeader
import com.media2359.intern0720.moneylover.network.MoneyLoverNetwork
import com.media2359.intern0720.moneylover.network.TransactionService
import com.media2359.intern0720.moneylover.ui.TransactionListAdapter
import com.media2359.intern0720.moneylover.utils.MoneyLoverUtils
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class TransactionsSessionFragment : Fragment() {
    val adapter = TransactionListAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transactions_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val request =
            MoneyLoverNetwork.createService(TransactionService::class.java)    // variable request
        val dateFrom = "2020-08-01T00:00:00.00Z"        // param
        val dateTo =   "2020-09-30T17:00:00.00Z"          // param
        val call = MoneyLoverUtils.moneyLoverManager?.getAccessToken()?.let {
            request.getTransactionByDate(
                it, dateFrom, dateTo
            )
        }

        call?.enqueue(object : Callback<List<TransactionResponse>> {
            override fun onResponse(
                call: retrofit2.Call<List<TransactionResponse>>,
                response: Response<List<TransactionResponse>>
            ) {
                if (response.isSuccessful) {
                    val itemlist = response.body()      // variable nhận data trả về
                    if (itemlist == null) {
                        return
                    }

                    val transactionModels = ArrayList<TransactionModel>()           // khởi tạo list TransactionModel
                    val header = TransactionHeader()            // khởi tạo đói tượng header
                    val subHeader = TransactionSubHeader()      // khởi tạo đói tượng subHeader
                    val items = TransactionItem()               // khởi tạo đói tượng item

                    for (id in itemlist.indices) {          // duyệt mảng tính tổng income và expense
                        if (itemlist.get(id).type == "EXPENSE") {
                            val expense = itemlist.get(id).amount * -1
                            header.totalExpense = header.totalExpense + expense
                        } else {
                            val income = itemlist.get(id).amount
                            header.totalIncome = header.totalIncome + income
                        }
                    }
                    header.total = header.totalIncome + header.totalExpense     // tính total
                    val total = header.total
                    transactionModels.add(header)

                    val sdf = SimpleDateFormat("yyyy-MM-ddThh:mm:ss.ZZZ")
                    val date = itemlist.get(0).date
                    val dateTime: Date = sdf.parse(date)


//                    recyclerListTransaction.apply {
//                        recyclerListTransaction.layoutManager = LinearLayoutManager(context)
//                        recyclerListTransaction.setHasFixedSize(true)
//                        recyclerListTransaction.adapter = TransactionListAdapter()
//                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<List<TransactionResponse>>, t: Throwable) {
//                showToastMessage(t.message)
                Log.w("API", "onFailure: " + t.message)
            }
        })
//        recyclerListTransaction.layoutManager = LinearLayoutManager(context)
//        recyclerListTransaction.setHasFixedSize(true)
//        recyclerListTransaction.adapter = adapter
    }
}