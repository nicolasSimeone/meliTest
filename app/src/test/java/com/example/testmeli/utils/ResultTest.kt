package com.example.testmeli.utils

import org.junit.Assert
import org.junit.Test
import java.lang.Exception
import java.lang.System.out


internal class ResultTest{
    val exception = Exception()
    val data = Any()
    @Test
    fun validateResultError(){
        val result = Result.Error(exception)
        Assert.assertEquals("Error(exception=$exception)",result.toString())
        //Mockito.`when`(error.exception).thenReturn()
    }

    @Test
    fun validateResultSuccess(){
        val result = Result.Success(data)
        Assert.assertEquals(data, result.data)
    }


}