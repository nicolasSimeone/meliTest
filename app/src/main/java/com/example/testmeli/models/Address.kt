package com.example.testmeli.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address (
    val state_name:String? = "",
    val city_name:String? = ""
):Parcelable