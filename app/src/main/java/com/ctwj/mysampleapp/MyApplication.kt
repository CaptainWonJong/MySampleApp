package com.ctwj.mysampleapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.ctwj.mysampleapp.di.DiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author CaptainWonJong@gmail.com
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(DiModule)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}