package com.example.testmeli.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results (
    var results: MutableList<Products>
) : Parcelable