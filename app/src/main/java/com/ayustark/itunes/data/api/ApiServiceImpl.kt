package com.ayustark.itunes.data.api

import com.ayustark.itunes.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getUsers(search: String): Single<List<User>> {
        var url = "https://itunes.apple.com/search?term=$search"
        return Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getObjectListSingle(User::class.java)
    }

}