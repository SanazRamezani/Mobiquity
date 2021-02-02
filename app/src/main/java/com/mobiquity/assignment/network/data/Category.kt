package com.mobiquity.assignment.network.data

import com.squareup.moshi.Json

data class Category(
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "products")
    val products: List<Product?>?
)
