package com.example.testmeli.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shipping (
    val free_shipping:Boolean? = false
):Parcelable