package com.ctwj.mysampleapp.di.part

import com.ctwj.mysampleapp.net.NetworkClient
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author CaptainWonJong@gmail.com
 */
val apiPart = module {
    single(createdAtStart = false) {
        get<Retrofit>().create(NetworkClient::class.java)
    }
}