package com.ctwj.mysampleapp.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ctwj.mysampleapp.R
import kotlinx.android.synthetic.main.item_main_bottom_tab.view.*
import kotlinx.android.synthetic.main.layout_main_bottom_tab.view.*

/**
 * @author CaptainWonJong@gmail.com
 */
class MainBottomTabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val tabs = listOf(Tabs.HOME, Tabs.CAMERA, Tabs.GALLERY, Tabs.SEARCH, Tabs.MY_PAGE)
    private var tabViews = mutableListOf<View>()
    var onTabClickListener: (() -> View)? = null

    init {
        View.inflate(context, R.layout.layout_main_bottom_tab, this)
        initTabs()
    }

    private fun initTabs() {
        tabs.forEach { tab ->
            View.inflate(context, R.layout.item_main_bottom_tab, null).apply {
                tv_tab.text = resources.getString(tab.tabName)
                iv_tab.setImageDrawable(resources.getDrawable(tab.imageRes))
            }.also {
                (ll_tabs.layoutParams as LayoutParams).weight = 1F
                ll_tabs.addView(it, ll_tabs.layoutParams)
                tabViews.add(it)
            }
        }
        tabViews.forEachIndexed { index, tabView ->
            tabView.setOnClickListener {
                onTabClickListener?.invoke()
                selectTab(tabView)
            }
        }
        selectTab(tabViews[0])
    }

    private fun selectTab(tabView: View) {
        tabViews.forEach {
            it.cl_tab.setBackgroundColor(resources.getColor(R.color.main_tab_unselected_background))
        }
        tabViews.filter { it == tabView }.map {
            it.cl_tab.setBackgroundColor(resources.getColor(R.color.main_tab_selected_background))
        }
    }
}

enum class Tabs(@DrawableRes val imageRes: Int, @StringRes val tabName: Int) {
    HOME(R.drawable.ic_home_black, R.string.home),
    CAMERA(R.drawable.ic_camera_black, R.string.camera),
    GALLERY(R.drawable.ic_gallery_black, R.string.gallery),
    SEARCH(R.drawable.ic_search_black, R.string.search),
    MY_PAGE(R.drawable.ic_my_page_black, R.string.my_page)
}