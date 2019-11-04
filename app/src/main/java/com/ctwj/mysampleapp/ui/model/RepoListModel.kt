package com.ctwj.mysampleapp.ui.model

data class RepoListModel(var repoList: ArrayList<RepoInfo>) {

    data class RepoInfo(
            var id: Int,
            var name: String) {
    }
}