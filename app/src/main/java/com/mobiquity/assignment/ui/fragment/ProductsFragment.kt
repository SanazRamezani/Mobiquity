package com.mobiquity.assignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiquity.assignment.R
import com.mobiquity.assignment.domain.State
import com.mobiquity.assignment.network.data.Product
import com.mobiquity.assignment.ui.MainViewModel
import com.mobiquity.assignment.ui.adapter.CategoryListAdapter
import com.mobiquity.assignment.ui.adapter.ProductListAdapter
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductsFragment : Fragment(), ProductListAdapter.OnProductSelectedListener {

    private val viewModel: MainViewModel by sharedViewModel()
    private val categoryListAdapter: CategoryListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        viewModel.getCategories()
        viewModel.categories.observe(viewLifecycleOwner, {
            swipeRefreshLayout.isRefreshing = it is State.Loading
            when (it) {
                is State.Success -> categoryListAdapter.addCategoryList(it.data)
                is State.Failure -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                else -> println("Loading")
            }
        })

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = categoryListAdapter
            categoryListAdapter.productSelectedListener = this@ProductsFragment
        }

        swipeRefreshLayout.setOnRefreshListener { viewModel.getCategories() }
    }

    override fun selected(item: Product) {
        viewModel.setProduct(item)
        Navigation.findNavController(requireParentFragment().requireView())
            .navigate(R.id.action_ProductsFragment_to_DetailsFragment)
    }
}
