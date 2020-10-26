package com.cuncisboss.rickandmorty.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.cuncisboss.rickandmorty.R

@BindingAdapter("setImageFromUrl")
fun ImageView.setImageFromUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(it)
            .transform(CircleCrop())
            .into(this)
    }
}

@BindingAdapter("species", "status")
fun TextView.setDescriptions(species: String, status: String) {
    text = String.format(this.context.getString(R.string.desc, species, status))
}