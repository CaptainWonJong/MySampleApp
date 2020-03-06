package com.ctwj.mysampleapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * @author CaptainWonJong@gmail.com
 */
interface ResourceUtils {

    fun getString(@StringRes resId: Int): String

    fun getColor(@ColorRes resId: Int): Int

    fun getDrawable(@DrawableRes resId: Int): Drawable?

    fun getStringArray(@ArrayRes resId: Int): Array<out String>
}

class ResourceUtilsImpl(private val context: Context) : ResourceUtils {
    override fun getString(resId: Int): String = context.getString(resId)

    override fun getColor(resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getDrawable(resId: Int): Drawable? = ContextCompat.getDrawable(context, resId)

    override fun getStringArray(resId: Int): Array<out String> = context.resources.getStringArray(resId)
}