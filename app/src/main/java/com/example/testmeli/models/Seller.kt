package com.example.testmeli.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Seller (
    val eshop: EShop? = EShop()
):Parcelable