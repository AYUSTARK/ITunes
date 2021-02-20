package com.ayustark.itunes.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayustark.itunes.data.model.ResultApi
import com.ayustark.itunes.data.model.Result
import com.ayustark.itunes.data.repository.MainRepository
import com.ayustark.itunes.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val mainRepository: MainRepository, private val search: String) :
    ViewModel() {

    private val users = MutableLiveData<Resource<List<Result>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        users.postValue(Resource.loading(null))
        /*compositeDisposable.add(
            mainRepository.getUsers("Arijit")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    println("Ab roo denge ${userList[1].avatar}")
                    users.postValue(Resource.success(userList))
                }, { throwable ->
                    users.postValue(Resource.error("Something Went Wrong", null))
                    println("Error $throwable")
                })
        )*/
        mainRepository.getLists(search)
            .enqueue(object : Callback<ResultApi> {
                override fun onResponse(call: Call<ResultApi>, response: Response<ResultApi>) {
                    val song = response.body()?.results
                    if (song != null) {
                        users.postValue(Resource.success(song))
                        println("Here's the list: ${song[1].artistId}")
                        Log.d("Success...Yipee", song.toString())
                    }
                }

                override fun onFailure(call: Call<ResultApi>, t: Throwable) {
                    users.postValue(Resource.error("Something Went Wrong", null))
                    Log.d("Gaya kaam se", "Error ${t.toString()}", t)
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUsers(): LiveData<Resource<List<Result>>> {
        return users
    }

}