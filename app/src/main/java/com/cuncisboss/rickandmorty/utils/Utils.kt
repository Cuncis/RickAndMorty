package com.cuncisboss.rickandmorty.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import timber.log.Timber

private const val TAG = "_logRickAndMorty"

fun showLog(message: String) {
    Timber.tag(TAG).d(message)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
