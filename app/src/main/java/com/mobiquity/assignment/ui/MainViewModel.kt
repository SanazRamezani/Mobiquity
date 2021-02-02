package com.mobiquity.assignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiquity.assignment.domain.GetCategoriesUseCase
import com.mobiquity.assignment.domain.State
import com.mobiquity.assignment.extensions.startUseCase
import com.mobiquity.assignment.network.data.Category
import com.mobiquity.assignment.network.data.Product

class MainViewModel(private val getCategoriesUseCase: GetCategoriesUseCase) : ViewModel() {

    val categories: MutableLiveData<State<List<Category>>> = MutableLiveData()
    val selectedProduct = MutableLiveData<Product>()

    fun getCategories() = startUseCase(getCategoriesUseCase, categories, null)

    fun setProduct(product: Product) {
        selectedProduct.value = product
    }
}