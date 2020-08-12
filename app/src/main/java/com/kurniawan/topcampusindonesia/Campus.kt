package com.kurniawan.topcampusindonesia

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Campus(
    var nama: String,
    var img: String
) : Parcelable