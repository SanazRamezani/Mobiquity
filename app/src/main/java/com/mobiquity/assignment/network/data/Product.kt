package com.mobiquity.assignment.network.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "categoryId")
    val categoryId: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "salePrice")
    val salePrice: Price?,
    @Json(name = "url")
    val url: String?
) : Parcelable {
    fun getImageUrl(): String {
        return "http://mobcategories.s3-website-eu-west-1.amazonaws.com$url"
    }
}
