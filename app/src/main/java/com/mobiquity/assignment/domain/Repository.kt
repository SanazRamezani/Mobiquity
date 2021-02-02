package com.mobiquity.assignment.domain

import com.mobiquity.assignment.network.data.Category

interface Repository {
    suspend fun getCategories(): State<List<Category>>
}