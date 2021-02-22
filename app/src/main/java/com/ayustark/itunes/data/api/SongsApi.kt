package com.ayustark.itunes.data.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object SongsApi {
    private const val base_url = "https://itunes.apple.com/"
    val songsApiService: SongsApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        songsApiService = retrofit.create(SongsApiService::class.java)
    }

}