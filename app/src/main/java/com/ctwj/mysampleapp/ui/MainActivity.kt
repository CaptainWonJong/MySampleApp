package com.ctwj.mysampleapp.ui

import android.os.Bundle
import android.widget.Toast
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.base.BaseActivity
import com.ctwj.mysampleapp.databinding.ActivityMainBinding
import com.ctwj.mysampleapp.util.MyEventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun observeLiveData() {
        viewModel.tabEvent.observe(this, MyEventObserver {event ->
            Toast.makeText(baseContext, "event : ${event}", Toast.LENGTH_LONG).show()
        })
    }
}