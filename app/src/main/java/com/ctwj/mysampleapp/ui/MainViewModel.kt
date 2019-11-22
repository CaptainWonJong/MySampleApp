package com.ctwj.mysampleapp.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ctwj.mysampleapp.base.BaseViewModel
import com.ctwj.mysampleapp.ui.model.ImageListModel
import com.ctwj.mysampleapp.util.MyLog
import com.ctwj.mysampleapp.util.MySingleLiveEvent

class MainViewModel: BaseViewModel(application = Application()) {

    private var _imageList = MutableLiveData<ImageListModel>()
    val imageList: LiveData<ImageListModel>
        get() = _imageList

    val tabEvent = MySingleLiveEvent<Int>()
    val testString = "testest"

    fun onTabClickEvent(eventType: Int) {
        MyLog.e(tabEvent.value.toString())
        tabEvent.postValue(eventType)
    }
}