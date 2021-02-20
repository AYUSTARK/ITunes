package com.ayustark.itunes.data.repository

import androidx.lifecycle.MutableLiveData
import com.ayustark.itunes.data.api.ApiHelper
import com.ayustark.itunes.data.model.Result
import com.ayustark.itunes.utils.Resource

class MainRepository(private val apiHelper: ApiHelper) {

    fun getLists(search: String, users: MutableLiveData<Resource<List<Result>>>) {
        return apiHelper.getList(search, users)
    }

}