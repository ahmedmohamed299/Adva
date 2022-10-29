package com.example.adva.presentation.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.adva.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImageInAdapter(imageView: ImageView, url: String?) {
    if (url != null && url.isNotBlank()) {
        Picasso
            .get()
            .load(url)
            .error(R.drawable.no_image_available)
            .placeholder(R.drawable.image_placeholder)
            .into(imageView)
    }else{
        Picasso
            .get()
            .load(R.drawable.no_image_available)
            .into(imageView)
    }
}