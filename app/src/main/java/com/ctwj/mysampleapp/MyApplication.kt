package com.ctwj.mysampleapp

import android.app.Application
import com.ctwj.mysampleapp.di.MyDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(MyDiModule)
        }
    }
}