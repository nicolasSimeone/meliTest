package com.example.testmeli.utils

import org.junit.Assert
import org.junit.Test


internal class PriceExtensionKtTest{
    @Test
    fun checkString(){
        var param = "10"
        val result = formatPrice(param)
        Assert.assertEquals("$10", result)
    }
}