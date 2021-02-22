package com.ayustark.itunes.ui.main.viewmodel

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayustark.itunes.data.repository.MainRepository
import com.ayustark.itunes.data.repository.SearchEntity
import com.ayustark.itunes.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) :
    ViewModel(), Observable {
    //val songs = mainRepository.songs

    private val users = MutableLiveData<Resource<List<SearchEntity>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            mainRepository.getLists(users, viewModelScope)
        }
    }

    fun getUsers(): LiveData<Resource<List<SearchEntity>>> {
        //songs
        return users
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}