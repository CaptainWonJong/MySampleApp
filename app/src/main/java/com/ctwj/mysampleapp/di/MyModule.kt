package com.ctwj.mysampleapp.di

import com.ctwj.mysampleapp.net.MyNetworkClient
import com.ctwj.mysampleapp.net.provideOkHttpClient
import com.ctwj.mysampleapp.ui.MainViewModel
import com.ctwj.mysampleapp.util.myPrefixUrl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module {
    single {
        Retrofit.Builder()
            .baseUrl(myPrefixUrl)
            .client(provideOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyNetworkClient::class.java)

    }
}

var viewModelPart = module {
    viewModel { MainViewModel() }
}

var MyDiModule = listOf(
    retrofitPart,
    viewModelPart
)
