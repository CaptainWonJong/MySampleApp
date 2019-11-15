package com.ctwj.mysampleapp.net

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface MyNetworkClient {

    // 저장소 리스트를 가져온다.
    @GET("imageList")
    fun getImageList(): Single<ResponseBody>
}