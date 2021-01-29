package com.example.testmeli.view

import android.content.Context
import android.widget.Button
import com.example.testmeli.R
import com.example.testmeli.models.Products
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.robolectric.Robolectric


internal class DetailActivityTest {

    private var activity: DetailActivity? = null
    private val data = Mockito.mock(Products::class.java)
    private val context = Mockito.mock(Context::class.java)

    @Test
    fun getData() {
        val result = Products()
        activity = Robolectric.buildActivity(DetailActivity::class.java)
            .create()
            .restart()
            .get()

        Assert.assertEquals(data, result)


    }

    @Test
    fun onCreate() {
        val title = context.getString(R.string.buy_now)

        activity = Robolectric.buildActivity(DetailActivity::class.java)
            .create()
            .restart()
            .get()

        val btn: Button = activity!!.findViewById(R.id.buy_btn)

        Assert.assertEquals(btn.text, title)


    }
}