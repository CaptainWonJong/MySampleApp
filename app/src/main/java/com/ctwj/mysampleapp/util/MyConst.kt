package com.ctwj.mysampleapp.util

import com.ctwj.mysampleapp.BuildConfig


private const val mockPrefixUrl = "https://my-json-server.typicode.com/CaptainWonJong/testServer"
private const val prodPrefixUrl = "https://my-json-server.typicode.com/CaptainWonJong/testServer"

val myPrefixUrl get() = if (isProd()) {
    prodPrefixUrl
} else mockPrefixUrl

fun isProd() : Boolean {
    return BuildConfig.FLAVOR == "prod"
}