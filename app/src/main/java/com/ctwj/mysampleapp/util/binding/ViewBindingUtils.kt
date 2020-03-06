package com.ctwj.mysampleapp.util.binding

import android.view.View
import androidx.databinding.BindingAdapter
import timber.log.Timber

/**
 * @author CaptainWonJong@gmail.com
 */
@BindingAdapter("android:visibility")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("setViewHeightRatio")
fun View.setViewHeightRatio(ratio: String) {
    try {
        val r = ratio.split(":")
        if (r.size == 2) {
            layoutParams.width = (r[0].toInt() * height) / r[1].toInt()
        }

    } catch (e: Exception) {
        Timber.e(e.toString())
    }
}

@BindingAdapter("setViewWidthRatio")
fun View.setViewWidthRatio(ratio: String) {
    try {
        val r = ratio.split(":")
        if (r.size == 2) {
            layoutParams.height = (r[1].toInt() * width) / r[0].toInt()
        }

    } catch (e: Exception) {
        Timber.e(e.toString())
    }
}