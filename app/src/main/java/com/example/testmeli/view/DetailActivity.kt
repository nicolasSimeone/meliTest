package com.example.testmeli.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.testmeli.R
import com.example.testmeli.models.Products
import com.example.testmeli.utils.formatPrice
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    var data:Products = Products()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        data = intent.getParcelableExtra("product")!!


        data.title.let {
            title_txt.text = it
        }

        data.thumbnail.let {
            Glide.with(this.applicationContext)
                .load(it)
                .into(imageDetail)
        }

        data.address.state_name.let {
            state.text = it
        }

        data.address.city_name.let {
            city.text = it
        }

        data.available_quantity.let {
            quantity.text = "Disponibles: " + it.toString()
        }

        data.price.let {
            price.text = formatPrice(it.toString())
        }


        buy_btn.setOnClickListener {
            Toast.makeText(applicationContext,getString(R.string.congratulations),Toast.LENGTH_SHORT).show()
        }
    }
}