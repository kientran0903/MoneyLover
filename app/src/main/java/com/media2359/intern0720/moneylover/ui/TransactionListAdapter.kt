package com.media2359.intern0720.moneylover.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.model.TransactionHeaderItem
import com.media2359.intern0720.moneylover.model.TransactionItem
import com.media2359.intern0720.moneylover.model.TransactionSubHeaderItem
import java.lang.reflect.Type

class TransactionListAdapter(
    var items: List<Any>?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_SUB_HEADER = 0
    val TYPE_ITEM = 1
    val TYPE_HEADER = 2

    var itemClickListener: ((position: Int, name: TransactionItem) -> Unit)? = null

    init {

        this.items = listOf(
            TransactionHeaderItem(800000,-1350000,-550000),
            TransactionSubHeaderItem("Sunday, 22 September 2019", 600000),
            TransactionItem("Sunday, 22 September 2019", R.drawable.salary, "Salary", 200000),
            TransactionItem("Sunday, 22 September 2019", R.drawable.price, "Sell", 200000),
            TransactionItem("Sunday, 22 September 2019", R.drawable.money, "Others", 200000),
            TransactionSubHeaderItem("Sunday, 29 September 2019", -1350000),
            TransactionItem("Sunday, 29 September 2019", R.drawable.bill, "Salary", -450000),
            TransactionItem("Sunday, 29 September 2019", R.drawable.plate, "Food & Drink", -450000),
            TransactionItem("Sunday, 29 September 2019", R.drawable.truck, "Transportation", -450000),
            TransactionSubHeaderItem("Sunday, 6 October 2019", 200000),
            TransactionItem("Sunday, 6 October 2019", R.drawable.salary, "Salary", 200000),
            TransactionItem("Sunday, 6 October 2019", R.drawable.plate, "Food & Drink", -200000),
            TransactionItem("Sunday, 6 October 2019", R.drawable.money, "Others", 200000),
        )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER ->{
                val view = inflater.inflate(R.layout.item_transaction_header,parent,false)
                MyViewHolderHeader(view)
            }
            TYPE_SUB_HEADER -> {
                val view = inflater.inflate(R.layout.item_transaction_sub_header, parent, false)
                MyViewHolderSubHeader(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.item_transaction, parent, false)
                MyViewHolderItem(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (holder) {
            is MyViewHolderHeader -> holder.bindViewHeader(items?.get(position)as TransactionHeaderItem)
            is MyViewHolderSubHeader -> holder.bindViewSubHeader(items?.get(position) as TransactionSubHeaderItem)
            is MyViewHolderItem -> holder.bindViewItem(items?.get(position) as TransactionItem)
        }

    }

    override fun getItemCount(): Int {
        if (items == null)
            return 0
        return items!!.count()
    }


    override fun getItemViewType(position: Int): Int {
        return when (items?.get(position)) {
            is TransactionHeaderItem -> TYPE_HEADER
            is TransactionSubHeaderItem -> TYPE_SUB_HEADER
            else -> TYPE_ITEM
        }

    }


    inner class MyViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val textInflow = itemView.findViewById<TextView>(R.id.tvAmountInflow)
        private val textOutflow = itemView.findViewById<TextView>(R.id.tvAmountOutflow)
        private val  textTotal = itemView.findViewById<TextView>(R.id.tvAmountTotal)

        fun bindViewHeader(itemView: TransactionHeaderItem){
            textInflow.text = itemView.inflow.toString()
            textOutflow.text = itemView.outflow.toString()
            textTotal.text = itemView.total.toString()
        }
    }

    inner class MyViewHolderSubHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textDate = itemView.findViewById<TextView>(R.id.tvDateSubHeader)
        private val textAmount = itemView.findViewById<TextView>(R.id.tvAmountSubHeader)

        fun bindViewSubHeader(itemView: TransactionSubHeaderItem) {
            textDate.text = itemView.date
            textAmount.text = itemView.amount.toString()
        }
    }

    inner class MyViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName = itemView.findViewById<TextView>(R.id.tvNameTransaction)
        private val imageIcon = itemView.findViewById<ImageView>(R.id.ivIconTransaction)
        private val textAmount = itemView.findViewById<TextView>(R.id.tvAmountTransaction)

        fun bindViewItem(item: TransactionItem) {
            textName.text = item.name
            imageIcon.setImageResource(item.icon)
            textAmount.text = item.amount.toString()
            itemClickListener?.invoke(position, item)
        }
    }
}

