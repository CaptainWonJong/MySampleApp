package com.ctwj.mysampleapp.util.binding

import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter

/**
 * @author CaptainWonJong@gmail.com
 */
@BindingAdapter("setOnItemSelectedListener")
fun setOnItemSelectedListener(view: Spinner, listener: AdapterView.OnItemSelectedListener) {
    view.onItemSelectedListener = listener
}