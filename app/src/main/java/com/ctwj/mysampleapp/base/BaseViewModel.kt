package com.ctwj.mysampleapp.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private var compositeDisposable = CompositeDisposable()

    protected var isLoading = MutableLiveData<Boolean>()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun removeDisposable(disposable: Disposable): Boolean {
        return compositeDisposable.remove(disposable)
    }

    fun clearDisposables() {
        compositeDisposable.clear()
    }
}