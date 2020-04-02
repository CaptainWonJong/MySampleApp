package com.ctwj.mysampleapp.ui.tab.home

import android.os.Bundle
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.ui.tab.MainStackFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : MainStackFragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
