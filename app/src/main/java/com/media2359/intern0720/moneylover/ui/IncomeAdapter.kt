package com.media2359.intern0720.moneylover.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.model.CategoryItem

class IncomeAdapter(var income: List<CategoryItem>) :
    RecyclerView.Adapter<IncomeAdapter.MyViewHolder>() {
    var itemClickListener: ((position: Int, name: CategoryItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return income.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(income[position], position)
    }

    fun setList(list: List<CategoryItem>) {
        income = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName = itemView.findViewById<TextView>(R.id.tvNameCategory)
        private val imageIcon = itemView.findViewById<ImageView>(R.id.ivIconCategory)

        fun bindView(income: CategoryItem, position: Int) {
            textName.text = income.name
            imageIcon.setImageResource(income.icon)
            itemView.setOnClickListener {
                itemClickListener?.invoke(position, income)

            }
        }
    }
}