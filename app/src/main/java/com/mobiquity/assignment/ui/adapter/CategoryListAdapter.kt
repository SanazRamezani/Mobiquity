package com.mobiquity.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.assignment.R
import com.mobiquity.assignment.network.data.Category
import com.mobiquity.assignment.network.data.Product
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

    private var items: MutableList<Category> = mutableListOf()
    var productSelectedListener: ProductListAdapter.OnProductSelectedListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items[position]
        item.let {
            holder.itemView.apply {
                categoryTitle.text = it.name
                it.products?.let { products ->
                    setRecyclerView(this, products)
                }
            }
        }
    }

    private fun setRecyclerView(view: View, products: List<Product?>) {
        view.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ProductListAdapter(products,
                object : ProductListAdapter.OnProductSelectedListener {
                    override fun selected(item: Product) {
                        productSelectedListener?.selected(item)
                    }
                })
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addCategoryList(categoryList: List<Category>) {
        items = categoryList.toMutableList()
        notifyDataSetChanged()
    }

    class CategoryViewHolder(view: View) :
        RecyclerView.ViewHolder(view)
}
