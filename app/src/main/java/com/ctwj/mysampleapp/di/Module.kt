package com.ctwj.mysampleapp.di

import com.ctwj.mysampleapp.di.part.*

/**
 * @author CaptainWonJong@gmail.com
 */
val DiModule = listOf(
    retrofitPart,
    apiPart,
    roomPart,
    resourcePart,
    viewModelPart
)
