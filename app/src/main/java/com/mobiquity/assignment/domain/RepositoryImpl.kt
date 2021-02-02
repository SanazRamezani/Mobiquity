package com.mobiquity.assignment.domain

import com.mobiquity.assignment.network.APIService
import com.mobiquity.assignment.network.data.Category

class RepositoryImpl(private val service: APIService) : Repository {
    override suspend fun getCategories(): State<List<Category>> {
        return try {
            State.Success(service.getCategories())
        } catch (e: Exception) {
            State.Failure(e.message.toString())
        }
    }
}