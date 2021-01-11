package com.example.testmeli.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmeli.R
import com.example.testmeli.models.Products
import com.example.testmeli.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var adapterProduct: ProductListAdapter

    private var productList : MutableList<Products> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapterProduct = ProductListAdapter(this, productList)


        findViewById<RecyclerView>(R.id.productsAll).apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager (context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterProduct
            adapterProduct.onItemClick = {
                    product ->
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)

            }

        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
        })

        viewModel.product.observe(this, Observer {
            productList = it.results
            adapterProduct.refreshList(productList)
        })

        viewModel.getAllProducts()
    }

    private fun filter(text :String){
        val filteredList = productList.filter { s -> s.title!!.toLowerCase().contains(text.toLowerCase()) } as MutableList<Products>
        adapterProduct.refreshList(filteredList)
    }
}