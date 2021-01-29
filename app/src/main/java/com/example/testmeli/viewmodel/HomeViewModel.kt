package com.example.testmeli.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmeli.models.Results
import com.example.testmeli.repositories.ProductRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val noticeRepository: ProductRepository) : ViewModel() {
    private val _product = MutableLiveData<Results>()
    val product : LiveData<Results>
        get() = _product




    fun getAllProducts(param:String){
        viewModelScope.launch {
            _product.value.let {
                _product.value = noticeRepository.getProducts(param)
            }
        }
    }
}