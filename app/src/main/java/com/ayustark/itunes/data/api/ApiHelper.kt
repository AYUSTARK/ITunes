package com.ayustark.itunes.data.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ayustark.itunes.data.model.ResultApi
import com.ayustark.itunes.data.repository.SearchEntity
import com.ayustark.itunes.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper(private val songsApi: SongsApi) {

    interface OnStatus {
        fun onSuccess(song: List<SearchEntity>)
        fun onFailure()
    }

    fun getList(
        search: String,
        users: MutableLiveData<Resource<List<SearchEntity>>>,
        status: OnStatus
    ) {
        users.postValue(Resource.loading(null))
        songsApi.songsApiService.getList(search)
            .enqueue(object : Callback<ResultApi> {
                override fun onResponse(call: Call<ResultApi>, response: Response<ResultApi>) {
                    val song = response.body()?.results!!
                    users.postValue(Resource.success(song))
                    status.onSuccess(song)
                    Log.d("Response", "${song.size}")
                }

                override fun onFailure(call: Call<ResultApi>, t: Throwable) {
                    status.onFailure()
                    users.postValue(Resource.error("Something Went Wrong", null))
                    Log.d("Response", "Error $t", t)
                }
            })
        //println("Hoja plzz $check")
    }

}