package com.media2359.intern0720.moneylover.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.model.CategoryItem
import com.media2359.intern0720.moneylover.ui.ExpenseAdapter
import kotlinx.android.synthetic.main.activity_select_category.*
import kotlinx.android.synthetic.main.category_item.*


class SelectCategoryActivity : AppCompatActivity() {

    companion object{
        const val KEY_CATEGORY_NAME = "KEY_CATEGORY_NAME"
        const val KEY_CATEGORY_TYPE = "KEY_CATEGORY_TYPE"
        const val CATEGORY_TYPE_EXPENSE = "EXPENSE"
        const val CATEGORY_TYPE_INCOME = "INCOME"
    }

    private var categoryType : String = CATEGORY_TYPE_EXPENSE
    private lateinit var expenseAdapter: ExpenseAdapter
     private val expense = listOf(
        CategoryItem(R.drawable.bill, "Bills & Utilities"),
        CategoryItem(R.drawable.truck, "Transportation"),
        CategoryItem(R.drawable.plate, "Food & Drink"),
        CategoryItem(R.drawable.money, "Others")
    )

    private val income = listOf(
        CategoryItem(R.drawable.salary, "Salary"),
        CategoryItem(R.drawable.gift, "Award/Gift"),
        CategoryItem(R.drawable.price, "Sell"),
        CategoryItem(R.drawable.money, "Others")
    )

    private fun show(item: CategoryItem ) {
        Toast.makeText(this, "${item.name} clicked!", Toast.LENGTH_SHORT).show()
    }

    private fun notifyDataSet() {
        expenseAdapter = ExpenseAdapter(expense)
//        incomeAdapter = IncomeAdapter(income)
        recyclerSelectTransaction.adapter = expenseAdapter
//        recyclerSelectTransaction.adapter = incomeAdapter
        recyclerSelectTransaction.layoutManager = LinearLayoutManager(this)
//        recyclerSelectTransaction.setHasFixedSize(true)

        expenseAdapter.itemClickListener = { position, item -> show(item)
            val returnIntent = Intent()
            val returnType = Intent()
            returnIntent.putExtra(KEY_CATEGORY_NAME, item.name)
            returnType.putExtra(KEY_CATEGORY_TYPE, categoryType)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_category)

        notifyDataSet()

        btnExpense.setOnClickListener {
            expenseAdapter.setList(expense)
            categoryType = CATEGORY_TYPE_EXPENSE
        }

        btnIncome.setOnClickListener {
            expenseAdapter.setList(income)
            categoryType = CATEGORY_TYPE_INCOME
        }

        btnBackSelect.setOnClickListener {
            Intent(applicationContext, AddTransactionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}