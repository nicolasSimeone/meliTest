package com.example.testmeli.view

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmeli.R
import com.example.testmeli.models.Products
import com.example.testmeli.utils.ConnectivityHelper
import com.example.testmeli.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var adapterProduct: ProductListAdapter

    private var productList : MutableList<Products> = arrayListOf()

    private var param : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


        checkConnectivity()


        adapterProduct = ProductListAdapter(this, productList)

        llProgressBar.visibility = View.VISIBLE

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

        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                filter(editText.text.toString())
                llProgressBar.visibility = View.VISIBLE
                editText.visibility = View.INVISIBLE
                productsAll.visibility = View.INVISIBLE
            }
            false
        }

        no_internet_detail.tryAgainAction = {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val connectivity = ConnectivityHelper.getConnectionType(this)
                if(connectivity == NetworkCapabilities.TRANSPORT_CELLULAR or NetworkCapabilities.TRANSPORT_WIFI){
                    no_internet_detail.visibility = View.GONE
                    relative_main.visibility = View.VISIBLE
                    recreate()
                }
            }else{
                val connetivity = ConnectivityHelper.getConnectionTypeSDK21(this)
                if(connetivity == 0 or ConnectivityManager.TYPE_WIFI){
                    no_internet_detail.visibility = View.GONE
                    relative_main.visibility = View.VISIBLE
                    recreate()
                }
            }
        }

        viewModel.product.observe(this, Observer {
            productList = it.results
            adapterProduct.refreshList(productList)
            llProgressBar.visibility = View.INVISIBLE
            editText.visibility = View.VISIBLE
            productsAll.visibility = View.VISIBLE
        })

        if(checkConnectivity()){
            if(savedInstanceState == null){
                viewModel.getAllProducts(ALL)
            }
        }


    }

    private fun filter(text :String){
        if(checkConnectivity()){
            if(text.isNotEmpty())
            {
                viewModel.getAllProducts(text)
            }
        }
    }

    private fun checkConnectivity(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (ConnectivityHelper.getConnectionType(this)) {
                NetworkCapabilities.TRANSPORT_WIFI -> {
                    no_internet_detail.visibility = View.GONE
                    relative_main.visibility = View.VISIBLE
                    return true
                }
                NetworkCapabilities.TRANSPORT_CELLULAR -> {
                    no_internet_detail.visibility = View.GONE
                    relative_main.visibility = View.VISIBLE
                    return true
                }

                else -> {
                    no_internet_detail.visibility = View.VISIBLE
                    relative_main.visibility = View.GONE
                    return false
                }
            }
        }else{
            when (ConnectivityHelper.getConnectionTypeSDK21(this)) {
                ConnectivityManager.TYPE_WIFI -> {
                    no_internet_detail.visibility = View.GONE
                    relative_main.visibility = View.VISIBLE
                    return true
                }
                0 -> {
                    no_internet_detail.visibility = View.GONE
                    relative_main.visibility = View.VISIBLE
                    return true
                }

                else -> {
                    no_internet_detail.visibility = View.VISIBLE
                    relative_main.visibility = View.GONE
                    return false
                }
            }
        }
    }

    companion object{
        private const val ALL = "Todos"
    }
}