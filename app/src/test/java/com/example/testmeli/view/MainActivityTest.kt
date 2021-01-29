package com.example.testmeli.view

import android.content.Context
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import com.example.testmeli.R
import org.junit.Assert
import org.junit.Test
import org.robolectric.Robolectric


internal class MainActivityTest {

    private val singleLine:Boolean = true
    private val context =
        ApplicationProvider.getApplicationContext<Context>()
    private var activity: MainActivity? = null

    @Test
    fun onCreate() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .restart()
            .get()

        val productSearch: EditText = activity!!.findViewById(R.id.editText)

        Assert.assertEquals(
            context.getString(R.string.search_product),
            productSearch.hint
        )
        Assert.assertEquals(
            singleLine,
            productSearch.isSingleLine
        )

    }
}