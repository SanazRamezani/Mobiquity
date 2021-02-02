package com.mobiquity.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.assignment.R
import com.mobiquity.assignment.extensions.loadImageCenterCrop
import com.mobiquity.assignment.network.data.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListAdapter(
    private val items: List<Product?>,
    private val productSelectedListener: OnProductSelectedListener
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = items[position]
        item?.let {
            holder.itemView.apply {
                productName.text = it.name
                productImage.loadImageCenterCrop(it.getImageUrl())
                setOnClickListener { _ ->
                    productSelectedListener.selected(it)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnProductSelectedListener {
        fun selected(item: Product)
    }
}