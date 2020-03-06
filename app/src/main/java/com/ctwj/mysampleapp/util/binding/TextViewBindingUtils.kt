package com.ctwj.mysampleapp.util.binding

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import timber.log.Timber
import java.util.*

/**
 * @author CaptainWonJong@gmail.com
 */
@BindingAdapter("isStrikeThrough")
fun TextView.isStrikeThrough(cancel: Boolean) {
    paintFlags = if (cancel) {
        Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        0
    }
}

@BindingAdapter("setPriceText")
fun TextView.setPriceText(num: Int?) {
    try {
        text = String.format(Locale.getDefault(), "%,d원", num)
    } catch (e: Exception) {
        Timber.e(e.toString())
    }
}

@BindingAdapter("setPriceText")
fun TextView.setPriceText(str: String?) {
    var price = 0
    try {
        price = str?.toInt() ?: 0
    } catch (e: Exception) {
        Timber.e(e.toString())
    }
    setPriceText(price)
}

// 서버에서 \n이 아닌 \\n 값으로 개행문자가 넘어와 생기는 문제가 발생할 때만 사용
@BindingAdapter("setReplaceNewlineText")
fun TextView.setReplaceNewlineText(str: String?) {
    try {
        text = str?.replace("\\n", "\n") ?: ""
    } catch (e: Exception) {
        Timber.e(e.toString())
    }
}

