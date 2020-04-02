package com.ctwj.mysampleapp.ui.tab

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.ctwj.mysampleapp.R

/**
 * @author CaptainWonJong@gmail.com
 */
open class MainStackFragment(@LayoutRes val layoutResId: Int = R.layout.fragment_parent_container) : Fragment(layoutResId) {

    val token by lazy { javaClass.name }

    open fun onBackPressed(): Boolean {
        return false
    }

    open fun getFragmentTransactionType(): FragmentTransactionType {
        return FragmentTransactionType.ADD
    }

    enum class FragmentTransactionType {
        ADD,
        REPLACE
    }
}