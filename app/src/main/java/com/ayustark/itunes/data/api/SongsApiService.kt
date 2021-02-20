package com.ayustark.itunes.data.api

import com.ayustark.itunes.data.model.ResultApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SongsApiService {
    @GET("search?")
    fun getList(@Query("term")search: String): Call<ResultApi>
}