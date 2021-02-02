package com.mobiquity.assignment.di

import com.mobiquity.assignment.domain.GetCategoriesUseCase
import com.mobiquity.assignment.domain.UseCase
import com.mobiquity.assignment.network.data.Category
import com.mobiquity.assignment.ui.MainViewModel
import com.mobiquity.assignment.ui.adapter.CategoryListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { CategoryListAdapter() }
    single<UseCase<Nothing?, List<Category>>>(named("getCategoriesUseCase")) {
        GetCategoriesUseCase(
            get()
        )
    }
    viewModel {
        MainViewModel(get(named("getCategoriesUseCase")))
    }
}
