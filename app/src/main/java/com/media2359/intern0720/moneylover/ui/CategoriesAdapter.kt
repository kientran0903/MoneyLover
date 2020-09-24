package com.media2359.intern0720.moneylover.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.media2359.intern0720.moneylover.R
import com.media2359.intern0720.moneylover.model.CategoryItem

class CategoriesAdapter(var items: List<CategoryItem>) :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {
    var itemClickListener: ((position: Int, name: CategoryItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(items[position], position)
    }

    fun setList(list: List<CategoryItem>) {
        items = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName = itemView.findViewById<TextView>(R.id.tvNameCategory)
        private val imageIcon = itemView.findViewById<ImageView>(R.id.ivIconCategory)

        fun bindView(item: CategoryItem, position: Int) {
            textName.text = item.name
            imageIcon.setImageResource(item.icon)
            itemView.setOnClickListener {
                itemClickListener?.invoke(position, item)

            }
        }
    }
}