package com.ctwj.mysampleapp.util.extension

import android.content.res.Resources
import android.util.DisplayMetrics
import kotlin.math.roundToInt

/**
 * @author CaptainWonJong@gmail.com
 */
val Int.dp: Int
    get() {
        return floatDp.roundToInt()
    }

val Float.dp: Int
    get() {
        return floatDp.roundToInt()
    }


val Int.floatDp: Float
    get() {
        val metrics = Resources.getSystem().displayMetrics
        return (this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
    }

val Float.floatDp: Float
    get() {
        val metrics = Resources.getSystem().displayMetrics
        return (this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
    }
