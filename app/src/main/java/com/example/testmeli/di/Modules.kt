package com.example.testmeli.di

import com.example.testmeli.repositories.ProductRepository
import com.example.testmeli.repositories.remote.ApiInstance
import com.example.testmeli.repositories.remote.ProductService
import com.example.testmeli.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { ApiInstance.create() }
}

val productModule = module {
    viewModel { HomeViewModel(get()) }
    single { ProductRepository(get()) }
    single { ProductService((get())) }
}