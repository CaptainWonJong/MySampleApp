package com.ctwj.mysampleapp.di.part

import com.ctwj.mysampleapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author CaptainWonJong@gmail.com
 */
val viewModelPart = module {
    viewModel { MainViewModel() }
}
