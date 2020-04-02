package com.ctwj.mysampleapp.ui.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.ui.common.Tabs
import com.ctwj.mysampleapp.ui.tab.home.HomeFragment
import com.ctwj.mysampleapp.ui.tab.mypage.MyPageFragment

/**
 * @author CaptainWonJong@gmail.com
 */
class MainContainerFragment : MainStackFragment(), MainTabFragmentManager.FragmentStackCallback {
    private var mainTabFragmentManager: MainTabFragmentManager? = null

    val mainStackFragment: MainStackFragment?
        get() = currentFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutResId, container, false)
        val rootView = FrameLayout(inflater.context).apply {
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            addView(view)
        }

        val rootFragment = when (tag) {
            Tabs.HOME.name -> HomeFragment()
            Tabs.MY_PAGE.name -> MyPageFragment()
            else -> HomeFragment()
        }
        mainTabFragmentManager = MainTabFragmentManager(childFragmentManager, R.id.fl_parent_container, rootFragment, this, lifecycle)
        return rootView
    }

    override fun getFragmentTransactionType(): FragmentTransactionType = FragmentTransactionType.ADD

    override fun onFragmentBackStackPopped(fragment: Fragment) {

    }

    fun showMainStackFragmentByParent(fragment: MainStackFragment) {
        if (isNotValidState) {
            return
        }
        val curFragment = currentFragment ?: return
        when (curFragment.getFragmentTransactionType()) {
            FragmentTransactionType.ADD -> mainTabFragmentManager?.addFragment(fragment)
            FragmentTransactionType.REPLACE -> mainTabFragmentManager?.replaceFragment(fragment)
        }
    }

    private val isNotValidState: Boolean
        get() = if (mainTabFragmentManager != null) {
            !isAdded
        } else true

    val currentFragment: MainStackFragment?
        get() = if (!isNotValidState) {
            null
        } else {
            mainTabFragmentManager?.currentFragment
        }

    fun removeMainStackFragment(fragment: MainStackFragment) {
        if (isNotValidState) {
            return
        }
        mainTabFragmentManager?.removeFragment(fragment)
    }

    fun getFragmentAt(index: Int): MainStackFragment? = if (isNotValidState) {
        null
    } else {
        mainTabFragmentManager?.getFragmentAt(index)
    }

    fun clearBackStackFragment() {
        if (isNotValidState) {
            return
        }
        mainTabFragmentManager?.clearBackStack()
    }

    fun popBackStackFragment() {
        if (isNotValidState) {
            return
        }
        mainTabFragmentManager?.popBackStackFragment()
    }

    val backStackFragmentsCount: Int
        get() = if (isNotValidState) {
            0
        } else {
            mainTabFragmentManager?.backStackFragmentsCount ?: 0
        }


    val totalFragmentsCount: Int
        get() = if (isNotValidState) {
            0
        } else {
            val backStackFragmentsCount = mainTabFragmentManager?.backStackFragmentsCount ?: 0
            backStackFragmentsCount + 1
        }


    val backStackFragment: MainStackFragment?
        get() = if (isNotValidState) {
            null
        } else {
            mainTabFragmentManager?.backStackFragment
        }

    override fun onBackPressed(): Boolean {
        val fragment = currentFragment
        return fragment != null && fragment.onBackPressed() || super.onBackPressed()
    }

    companion object {
        fun newInstance(): MainContainerFragment = MainContainerFragment()
    }
}