package com.ayustark.itunes.data.api

import com.ayustark.itunes.data.model.ResultApi
import retrofit2.Call

class ApiHelper(private val songsApi: SongsApi) {

    fun getList(search: String): Call<ResultApi> {
        val songs = songsApi.songsApiService.getList(search)
        return songs
    }

}