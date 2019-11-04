package com.ctwj.mysampleapp.di

import com.ctwj.mysampleapp.net.MyRepository
import com.ctwj.mysampleapp.net.provideOkHttpClient
import com.ctwj.mysampleapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(provideOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyRepository::class.java)

    }
}

var viewModelPart = module {
    viewModel { MainViewModel(get()) }
}

var MyDiModule = listOf(
    retrofitPart,
    viewModelPart
)
