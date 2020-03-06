package com.ctwj.mysampleapp.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.util.extension.dp

/**
 * @author CaptainWonJong@gmail.com
 */
@BindingAdapter("setImageUri")
fun ImageView.setImageUri(uri: String?) {
    Glide.with(context)
        .load(uri)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .into(this)
}

@BindingAdapter("setRoundCornerImageUri", "setRoundCornerImageRadius")
fun ImageView.setRoundCornerImageUri(uri: String?, radius: Int?) {
    Glide.with(context)
        .load(uri)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .transform(CenterInside(), RoundedCorners(radius?.dp ?: 0))
        .into(this)
}