package com.ctwj.mysampleapp.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.ui.tab.home.HomeFragment
import com.ctwj.mysampleapp.ui.tab.mypage.MyPageFragment
import kotlinx.android.synthetic.main.item_main_bottom_tab.view.*
import kotlinx.android.synthetic.main.layout_main_bottom_tab.view.*

/**
 * @author CaptainWonJong@gmail.com
 */
class MainBottomTabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var tabViews = mutableListOf<View>()

    var initTabIndex  = 0

    val tabs = listOf(Tabs.HOME, Tabs.CAMERA, Tabs.GALLERY, Tabs.SEARCH, Tabs.MY_PAGE)
    val selectedTabIndex = MutableLiveData(initTabIndex)

    init {
        View.inflate(context, R.layout.layout_main_bottom_tab, this)
        initTabs()
    }

    private fun initTabs() {
        tabs.forEachIndexed { index, tab ->
            View.inflate(context, R.layout.item_main_bottom_tab, null).apply {
                tv_tab.text = resources.getString(tab.tabName)
                iv_tab.setImageDrawable(resources.getDrawable(tab.imageRes))
                setOnClickListener { selectTab(index) }
            }.also {
                (ll_tabs.layoutParams as LayoutParams).weight = 1F
                ll_tabs.addView(it, ll_tabs.layoutParams)
                tabViews.add(it)
            }
        }
        selectTab(initTabIndex)
    }

    fun selectTab(index: Int) {
        tabViews.forEach { v ->
            v.cl_tab.setBackgroundColor(resources.getColor(R.color.main_tab_unselected_background))
        }
        tabViews[index].cl_tab.setBackgroundColor(resources.getColor(R.color.main_tab_selected_background))
        selectedTabIndex.value = index
    }
}

enum class Tabs(@DrawableRes val imageRes: Int, @StringRes val tabName: Int, @LayoutRes val layoutRes: Int? = null) {
    HOME(R.drawable.ic_home_black, R.string.home, HomeFragment().layoutResId),
    CAMERA(R.drawable.ic_camera_black, R.string.camera),
    GALLERY(R.drawable.ic_gallery_black, R.string.gallery),
    SEARCH(R.drawable.ic_search_black, R.string.search),
    MY_PAGE(R.drawable.ic_my_page_black, R.string.my_page, MyPageFragment().layoutResId)
}