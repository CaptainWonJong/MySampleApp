package com.ctwj.mysampleapp.ui

import android.os.Bundle
import android.view.View
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.base.BaseActivity
import com.ctwj.mysampleapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun observeLiveData() {

    }

    fun onClick(view: View) {
        viewModel.requestRepoList()
    }
}