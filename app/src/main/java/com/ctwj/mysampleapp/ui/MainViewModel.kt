package com.ctwj.mysampleapp.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ctwj.mysampleapp.base.BaseViewModel
import com.ctwj.mysampleapp.ui.model.ImageListModel
import com.ctwj.mysampleapp.util.MyEvent

class MainViewModel : BaseViewModel(application = Application()) {

    private var _imageList = MutableLiveData<ImageListModel>()
    val imageList: LiveData<ImageListModel>
        get() = _imageList


    private val _tabEvent = MutableLiveData<MyEvent<Int>>()
    val tabEvent: LiveData<MyEvent<Int>> = _tabEvent

    fun onTabClickEvent(eventType: Int) {
        _tabEvent.value = MyEvent(eventType)
    }
}