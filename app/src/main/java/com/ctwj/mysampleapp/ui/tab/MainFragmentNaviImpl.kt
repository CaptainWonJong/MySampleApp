package com.ctwj.mysampleapp.ui.tab

import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.ui.MainActivity
import com.ctwj.mysampleapp.ui.common.FragmentNavigator
import com.ctwj.mysampleapp.ui.common.Tabs

/**
 * @author CaptainWonJong@gmail.com
 */
class MainFragmentNaviImpl(
    private val activity: MainActivity
) : FragmentNavigator {

    private var fragment: MainContainerFragment? = null

    override fun navigate(contentHolderId: String) {
        pushFragment(contentHolderId)
    }

    override fun navigate(mainStackFragment: MainStackFragment) {
        fragment?.showMainStackFragmentByParent(mainStackFragment)
    }

    override fun navigateBack() {
        popFragment()
    }

    override fun clearStack(contentHolderId: String) {
        clearFragments()
    }

    override fun getBackHome() {
        changeTab(Tabs.HOME)
        clearFragments()
    }

    fun getSelectedFragmentTag(): String? = fragment?.tag

    fun changeTab(tabs: Tabs) {
        when (tabs) {
            Tabs.HOME, Tabs.SEARCH, Tabs.MY_PAGE -> {
                pushFragment(tabs.name)
            }
            Tabs.CAMERA -> {
                // TODO: 카메라 오픈
            }
            Tabs.GALLERY -> {
                // TODO: 갤러리 오픈
            }
        }
    }

    fun onBackPressed(): Boolean = popFragment()

    private fun popFragment(): Boolean {
        fragment?.let {
            it.mainStackFragment?.also { mainStackFragment ->
                if (mainStackFragment.onBackPressed()) {
                    return true
                }

                if (it.backStackFragmentsCount > 0) {
                    it.popBackStackFragment()
                    return true
                }
            }

            if (Tabs.HOME.name == it.tag) {
                return false
            }

            val homeContentHolder = getParentFragmentByTag(Tabs.HOME.name)
            if (homeContentHolder != null) {
                activity.supportFragmentManager.beginTransaction().apply {
                    remove(it)
                    show(homeContentHolder)
                    commitAllowingStateLoss()
                }
                this.fragment = homeContentHolder
                return true
            }
        }
        return false
    }

    private fun pushFragment(fragmentTag: String) {
        val mainContainerFragment = fragment
        if (mainContainerFragment != null && fragmentTag == mainContainerFragment.tag) {
            return
        }

        val ft = activity.supportFragmentManager.beginTransaction()
        mainContainerFragment?.let {
            ft.hide(it)
        }

        var selectedStackHolder = getParentFragmentByTag(fragmentTag)
        with(ft) {
            if (selectedStackHolder != null) {
                show(selectedStackHolder!!)
            } else {
                selectedStackHolder = MainContainerFragment.newInstance()
                add(R.id.fl_fragment_container_main, selectedStackHolder!!, fragmentTag)
            }
            commitNowAllowingStateLoss()
        }
        fragment = selectedStackHolder
    }

    private fun clearFragments() {
        fragment?.clearBackStackFragment()
    }

    private fun getParentFragmentByTag(tag: String): MainContainerFragment? =
        activity.supportFragmentManager.findFragmentByTag(tag) as MainContainerFragment?
}