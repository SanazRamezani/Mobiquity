package com.mobiquity.assignment.network

import com.mobiquity.assignment.network.data.Category
import retrofit2.http.GET

interface APIService {
    @GET(".")
    suspend fun getCategories(): List<Category>
}