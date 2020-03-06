package com.ctwj.mysampleapp.di.part

import com.ctwj.mysampleapp.util.ResourceUtilsImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * @author CaptainWonJong@gmail.com
 */
val resourcePart = module {
    single { ResourceUtilsImpl(androidApplication()) }
}