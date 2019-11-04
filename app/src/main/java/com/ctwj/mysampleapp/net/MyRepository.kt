package com.ctwj.mysampleapp.net

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface MyRepository {

    // 저장소 리스트를 가져온다.
    @GET("users/{id}/repos")
    fun getRepoList(
            @Path("id") id: String
    ): Single<ResponseBody>

    // 저장소의 커밋 리스트를 가져온다.
    @GET("repos/{id}/{repo}/commits")
    fun getCommitList(
            @Path("id") id: String,
            @Path("repo") repo: String
    ): Single<ResponseBody>
}