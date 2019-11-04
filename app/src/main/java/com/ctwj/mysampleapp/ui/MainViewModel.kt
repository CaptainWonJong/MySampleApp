package com.ctwj.mysampleapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ctwj.mysampleapp.base.BaseViewModel
import com.ctwj.mysampleapp.net.MyRepository
import com.ctwj.mysampleapp.ui.model.RepoListModel
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private var repo: MyRepository) : BaseViewModel() {

    private var _repoList = MutableLiveData<RepoListModel>()
    val repoList: LiveData<RepoListModel>
        get() = _repoList

    fun requestRepoList(id: String) {
        addDisposable(
            repo.getRepoList(id)
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

    fun requestCommitList() {

    }
}