package com.udacity.shoestore.models

import android.os.Parcelable
// import kotlinx.android.parcel.Parcelize              // deprecated
import kotlinx.parcelize.Parcelize                      // replaced with kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable
