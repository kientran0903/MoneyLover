package com.media2359.intern0720.moneylover.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.model.CategoryItem
import com.media2359.intern0720.moneylover.ui.CategoriesAdapter
import kotlinx.android.synthetic.main.activity_select_category.*


class SelectCategoryActivity : AppCompatActivity() {

    companion object{
        const val KEY_CATEGORY_NAME = "KEY_CATEGORY_NAME"
        const val KEY_CATEGORY_TYPE = "KEY_CATEGORY_TYPE"
        const val CATEGORY_TYPE_EXPENSE = "EXPENSE"
        const val CATEGORY_TYPE_INCOME = "INCOME"
    }

    private var categoryType : String = CATEGORY_TYPE_EXPENSE
    private lateinit var categoriesAdapter: CategoriesAdapter
     private val expense = listOf(
        CategoryItem(R.drawable.bill, "RESTAURANT"),
        CategoryItem(R.drawable.truck, "TRANSPORTATION"),
        CategoryItem(R.drawable.plate, "SHOPPING"),
        CategoryItem(R.drawable.money, "OTHERS")
    )

    private val income = listOf(
        CategoryItem(R.drawable.salary, "SALARY"),
        CategoryItem(R.drawable.gift, "FREELANCE"),
        CategoryItem(R.drawable.price, "INVESTMENT"),
        CategoryItem(R.drawable.money, "OTHERS")
    )

    private fun show(item: CategoryItem ) {
        Toast.makeText(this, "${item.name} clicked!", Toast.LENGTH_SHORT).show()
    }

    private fun notifyDataSet() {
        categoriesAdapter = CategoriesAdapter(expense)
//        incomeAdapter = IncomeAdapter(income)
        recyclerSelectTransaction.adapter = categoriesAdapter
//        recyclerSelectTransaction.adapter = incomeAdapter
        recyclerSelectTransaction.layoutManager = LinearLayoutManager(this)
//        recyclerSelectTransaction.setHasFixedSize(true)

        categoriesAdapter.itemClickListener = { position, item -> show(item)
            val returnIntent = Intent()
            returnIntent.putExtra(KEY_CATEGORY_NAME, item.name)
            returnIntent.putExtra(KEY_CATEGORY_TYPE, categoryType)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_category)

        notifyDataSet()

        btnExpense.setOnClickListener {
            categoriesAdapter.setList(expense)
            categoryType = CATEGORY_TYPE_EXPENSE
        }

        btnIncome.setOnClickListener {
            categoriesAdapter.setList(income)
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