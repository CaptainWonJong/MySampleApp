package com.ctwj.mysampleapp.util.binding

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter

/**
 * @author CaptainWonJong@gmail.com
 */
@BindingAdapter("android:visibility")
fun ViewGroup.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}