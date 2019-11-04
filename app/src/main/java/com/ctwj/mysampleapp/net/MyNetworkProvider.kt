package com.ctwj.mysampleapp.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .run {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        build()
}