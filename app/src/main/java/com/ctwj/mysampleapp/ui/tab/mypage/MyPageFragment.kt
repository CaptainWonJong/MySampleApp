package com.ctwj.mysampleapp.ui.tab.mypage

import android.os.Bundle
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.ui.tab.MainStackFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : MainStackFragment(R.layout.fragment_my_page) {
    private val viewModel: MyPageViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
