package com.mobiquity.assignment.network.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    @Json(name = "amount")
    val amount: String?,
    @Json(name = "currency")
    val currency: String?
) : Parcelable {
    fun getPriceText(): String {
        return "$currency $amount"
    }
}