package com.ayustark.itunes.data.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ayustark.itunes.data.model.Result
import com.ayustark.itunes.data.model.ResultApi
import com.ayustark.itunes.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper(private val songsApi: SongsApi) {

    interface OnStatus {
        fun onSuccess(song: List<Result>)
        fun onFailure()
    }

    fun getList(search: String, users: MutableLiveData<Resource<List<Result>>>, status: OnStatus) {
        users.postValue(Resource.loading(null))
        songsApi.songsApiService.getList(search)
            .enqueue(object : Callback<ResultApi> {
                override fun onResponse(call: Call<ResultApi>, response: Response<ResultApi>) {
                    val song = response.body()?.results!!
                    status.onSuccess(song)
                    users.postValue(Resource.success(song))
                    Log.d("Response", song.toString())
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