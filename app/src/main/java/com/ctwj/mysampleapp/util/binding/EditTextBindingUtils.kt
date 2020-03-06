package com.ctwj.mysampleapp.util.binding

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * @author CaptainWonJong@gmail.com
 */

@BindingAdapter("setOnFocusChangeListener")
fun setOnFocusChangeListener(editText: EditText, listener: View.OnFocusChangeListener) {
    editText.onFocusChangeListener = listener
}

@BindingAdapter("setOnEditorActionListener")
fun setOnEditorActionListener(editText: EditText, listener: TextView.OnEditorActionListener) {
    editText.setOnEditorActionListener(listener)
}