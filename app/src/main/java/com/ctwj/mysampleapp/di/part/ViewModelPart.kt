package com.ctwj.mysampleapp.di.part

import com.ctwj.mysampleapp.ui.MainViewModel
import com.ctwj.mysampleapp.ui.tab.home.HomeViewModel
import com.ctwj.mysampleapp.ui.tab.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author CaptainWonJong@gmail.com
 */
val activityViewModelPart = module {
    viewModel { MainViewModel() }
}

val fragmentViewModelPart = module {
    viewModel { HomeViewModel() }
    viewModel { MyPageViewModel() }
}
