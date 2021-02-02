package com.mobiquity.assignment.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mobiquity.assignment.R

fun ImageView.loadImageCenterCrop(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_noimage)
        .centerCrop()
        .into(this)
}

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .placeholder(R.drawable.ic_noimage)
        .error(R.drawable.ic_noimage)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}
