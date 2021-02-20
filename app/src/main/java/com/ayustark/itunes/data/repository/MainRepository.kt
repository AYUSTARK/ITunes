package com.ayustark.itunes.data.repository

import com.ayustark.itunes.data.api.ApiHelper
import com.ayustark.itunes.data.model.ResultApi
import retrofit2.Call

class MainRepository(private val apiHelper: ApiHelper) {

    fun getLists(search: String):Call<ResultApi> {
        return apiHelper.getList(search)
    }

}