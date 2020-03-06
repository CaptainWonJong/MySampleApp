package com.ctwj.mysampleapp.ui

import android.app.Application
import com.ctwj.mysampleapp.base.BaseViewModel
import com.ctwj.mysampleapp.util.SingleLiveData

/**
 * @author CaptainWonJong@gmail.com
 */
class MainViewModel : BaseViewModel(application = Application()) {
    var tabClick = SingleLiveData<Unit>()
    fun onTabClick(){
        tabClick.call()
    }
}