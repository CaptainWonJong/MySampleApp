package com.ctwj.mysampleapp.ui.common

import com.ctwj.mysampleapp.ui.tab.MainStackFragment

/**
 * @author CaptainWonJong@gmail.com
 */
interface FragmentNavigator {

    fun navigate(contentHolderId: String)

    fun navigate(mainStackFragment: MainStackFragment)

    fun navigateBack()

    fun clearStack(contentHolderId: String)

    fun getBackHome()
}