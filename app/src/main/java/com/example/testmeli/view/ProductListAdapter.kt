package com.example.testmeli.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmeli.R
import com.example.testmeli.models.Products
import com.example.testmeli.utils.formatPrice
import kotlinx.android.synthetic.main.list_item.view.*

class ProductListAdapter(private val context: Context, private var productList: MutableList<Products>): RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {


    var onItemClick:((Products) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    fun refreshList(noticeList: MutableList<Products>) {
        this.productList.clear()
        this.productList = noticeList.toMutableList()
        notifyDataSetChanged()
    }

    fun getItem(position: Int) : Products {
        return productList[position]
    }


    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        holder.txtTitle.text = productList[position].title
        holder.txtSubtitle.text = formatPrice(productList[position].price.toString())

        Glide.with(this.context)
            .load(productList[position].thumbnail)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imgView)
    }

    inner class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        var txtTitle: TextView = itemView.title
        var txtSubtitle: TextView = itemView.subtitle
        var imgView:ImageView = itemView.imgView

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(productList[adapterPosition])
            }
        }

    }

}