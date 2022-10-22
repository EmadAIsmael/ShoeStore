package com.udacity.shoestore.models

import android.os.Parcelable
// import kotlinx.android.parcel.Parcelize              // deprecated
import kotlinx.parcelize.Parcelize                      // replaced with kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(
    var company: String = "",
    var name: String = "",
    var size: String = "0.0",
    var description: String = ""
) : Parcelable

