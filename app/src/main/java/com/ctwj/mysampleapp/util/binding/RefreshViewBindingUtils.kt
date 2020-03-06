package com.ctwj.mysampleapp.util.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * @author CaptainWonJong@gmail.com
 */
@BindingAdapter("onRefreshListener")
fun setOnRefreshListener(view: SwipeRefreshLayout, onRefreshListener: SwipeRefreshLayout.OnRefreshListener) {
    view.setOnRefreshListener(onRefreshListener)
}

@BindingAdapter("isRefresh")
fun isRefresh(view: SwipeRefreshLayout, isRefresh: Boolean) {
    view.isRefreshing = isRefresh
}