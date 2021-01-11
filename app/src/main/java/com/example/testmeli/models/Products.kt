package com.example.testmeli.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products (
    val title:String? = "",
    val price:Double = 0.0,
    val available_quantity:Int = 0,
    val condition:String? = "",
    val thumbnail:String? = "",
    val address: Address = Address()
) : Parcelable