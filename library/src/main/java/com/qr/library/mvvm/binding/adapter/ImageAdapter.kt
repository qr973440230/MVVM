package com.qr.library.mvvm.binding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageAdapter {

    @BindingAdapter("imgUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        Picasso.get().load(url).into(view)
    }

    @BindingAdapter("imgUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: Int) {
        Picasso.get().load(url).into(view)
    }
}