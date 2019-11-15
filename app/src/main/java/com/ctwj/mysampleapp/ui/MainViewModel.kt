package com.ctwj.mysampleapp.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ctwj.mysampleapp.base.BaseViewModel
import com.ctwj.mysampleapp.net.MyNetworkClient
import com.ctwj.mysampleapp.ui.model.ImageListModel
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private var repo: MyNetworkClient) : BaseViewModel(application = Application()) {

    private var _imageList = MutableLiveData<ImageListModel>()
    val imageList: LiveData<ImageListModel>
        get() = _imageList


    fun requestRepoList() {
        addDisposable(
            repo.getImageList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // 성공

                    val response = Gson().toJson(it.string())


                    for (i in 0..1) {

                    }
                }, {
                    // 실패
                })
        )
    }
}