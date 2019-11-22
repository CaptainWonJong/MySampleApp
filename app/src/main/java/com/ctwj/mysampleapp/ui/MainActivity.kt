package com.ctwj.mysampleapp.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.base.BaseActivity
import com.ctwj.mysampleapp.databinding.ActivityMainBinding
import com.ctwj.mysampleapp.util.MyLog
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyLog.e(viewModel.testString)
    }

    override fun observeLiveData() {
        viewModel.tabEvent.observe(this, Observer { event ->
            event ?: return@Observer
            toast(event)
        })
    }
}