package com.ayustark.itunes.data.api

import com.ayustark.itunes.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}