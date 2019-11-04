package com.ctwj.mysampleapp.util

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("android:visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.setVisibility(if (visible) View.VISIBLE else View.GONE)
}