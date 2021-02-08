package com.example.testmeli.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EShop(
    val nick_name:String? = "",
    val eshop_logo_url:String? =""
):Parcelable