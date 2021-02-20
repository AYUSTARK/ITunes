package com.ayustark.itunes.data.api

import android.util.Log
import com.ayustark.itunes.data.model.Result
import com.ayustark.itunes.data.model.ResultApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper(private val songsApi:SongsApi) {

    fun getList(search: String):Call<ResultApi> {
        val songs = songsApi.songsApiService.getList(search)
        return songs
        //println("Here's list: ${song?.results?.get(49)?.artistName}")
    }

}