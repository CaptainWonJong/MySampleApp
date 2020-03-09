package com.ctwj.mysampleapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.base.BaseActivity
import com.ctwj.mysampleapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author CaptainWonJong@gmail.com
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

    }

    override fun observeLiveData() {
        viewModel.tabClick.observe(this, Observer {
            Toast.makeText(this, tab_main.selectedTabIndex, Toast.LENGTH_LONG).show()
        })
    }
}