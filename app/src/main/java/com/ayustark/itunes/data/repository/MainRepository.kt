package com.ayustark.itunes.data.repository

import com.ayustark.itunes.data.api.ApiHelper
import com.ayustark.itunes.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(search: String): Single<List<User>> {
        return apiHelper.getUsers(search)
    }

}