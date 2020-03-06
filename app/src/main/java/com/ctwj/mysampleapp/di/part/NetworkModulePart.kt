package com.ctwj.mysampleapp.di.part

import com.ctwj.mysampleapp.util.myPrefixUrl
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author CaptainWonJong@gmail.com
 */

const val CONNECT_TIMEOUT = 30L // 타임아웃 30초

val retrofitPart = module {
    single {
        Retrofit.Builder()
            .baseUrl(myPrefixUrl)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single {
        GsonBuilder()
            //.registerTypeAdapter(ProductListVO::class.java, ProductListDeserializer()) // TODO: Deserializer 필요할 경우 추가
            .create()
    }

    single {
        OkHttpClient.Builder().run {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor(get<Interceptor>(named("HeaderRequest")))
            addInterceptor(get<Interceptor>(named("HeaderResponse")))
            build()
        }
    }

    // Common Header
    single(named("HeaderRequest")) {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Content-Type", "application/json")
            }.build())
        }
    }


    single(named("HeaderResponse")) {
        Interceptor { chain ->
            val response = chain.proceed(chain.request())

            if (response.code == 200) {
                // TODO: Success Process
            } else {
                // TODO: Fail Process
                Timber.e("Network Fail Code: %s", response.code)
            }
            return@Interceptor response
        }
    }
}