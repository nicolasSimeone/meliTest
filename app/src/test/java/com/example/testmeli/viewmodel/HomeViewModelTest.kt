package com.example.testmeli.viewmodel


import com.example.testmeli.repositories.ProductRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class HomeViewModelTest {

    private val repository = Mockito.mock(ProductRepository::class.java)
    private val viewModel = HomeViewModel(repository)

    @Test
     suspend fun getAllProducts() {
        val param = ""
        Mockito.`when`(repository.getProducts(Mockito.any())).thenReturn(null)
        viewModel.getAllProducts(param)
        Assert.assertNull(viewModel.product.value)
    }
}