package com.nahar.first_2022

import android.graphics.PostProcessor
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.net.CacheRequest

interface RetrofitInterface {
    @get:GET("posts")
    val posts : Call<List<PostModel?>?>?

    @POST("posts")
    fun posts(@Body user: PostModel?): Call<PostModel>

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
    }

/*
@POST("/api/v1/create")
suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>*/
