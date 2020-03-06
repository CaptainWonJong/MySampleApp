package com.ctwj.mysampleapp.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * @author CaptainWonJong@gmail.com
 */
@BindingAdapter("addOnScrollListener")
fun addOnScrollListener(view: RecyclerView, onScrollListener: RecyclerView.OnScrollListener) {
    view.addOnScrollListener(onScrollListener)
}