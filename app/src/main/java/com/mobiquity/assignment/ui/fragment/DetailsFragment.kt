package com.mobiquity.assignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mobiquity.assignment.R
import com.mobiquity.assignment.extensions.loadImage
import com.mobiquity.assignment.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.details_fragment_label)
        setupView()
    }

    private fun setupView() {
        viewModel.selectedProduct.observe(viewLifecycleOwner, {
            productName.text = it.name
            productDescription.text = it.description
            productPrice.text = it.salePrice?.getPriceText()
            productImageView.loadImage(it.getImageUrl())
        })
    }
}
