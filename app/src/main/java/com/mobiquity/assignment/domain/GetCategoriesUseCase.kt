package com.mobiquity.assignment.domain

import com.mobiquity.assignment.network.data.Category

class GetCategoriesUseCase(private val repository: Repository) :
    UseCase<Nothing?, List<Category>>() {
    override suspend fun execute(params: Nothing?): State<List<Category>> {
        return repository.getCategories()
    }
}