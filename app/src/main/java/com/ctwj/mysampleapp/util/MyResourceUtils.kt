package com.ctwj.mysampleapp.util

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.ctwj.mysampleapp.MyApplication


object MyResourceUtils {

    fun getString(app: MyApplication, @StringRes resId: Int): String {
        return app.getString(resId)
    }

    fun getColor(app: MyApplication, @ColorRes resId: Int): Int {
        return ContextCompat.getColor(app, resId)
    }

    fun getDrawable(app: MyApplication, @DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(app, resId)
    }
}
