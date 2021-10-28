package com.mayouf.candyspace.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImageFull(url: String?) =
    Picasso.get().load(url).into(this)
