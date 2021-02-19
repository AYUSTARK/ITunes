package com.ayustark.itunes.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getUsers(search: String) = apiService.getUsers(search)

}