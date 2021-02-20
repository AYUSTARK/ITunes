package com.ayustark.itunes.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayustark.itunes.data.model.Result
import com.ayustark.itunes.data.repository.MainRepository
import com.ayustark.itunes.utils.Resource

class MainViewModel(private val mainRepository: MainRepository, search: String) :
    ViewModel() {

    private val users = MutableLiveData<Resource<List<Result>>>()

    init {
        fetchUsers(search)
    }

    private fun fetchUsers(search: String) {
        mainRepository.getLists(search, users)
    }

    fun getUsers(): LiveData<Resource<List<Result>>> {
        return users
    }

}