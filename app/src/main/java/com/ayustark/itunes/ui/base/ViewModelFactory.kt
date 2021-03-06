package com.ayustark.itunes.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ayustark.itunes.data.repository.MainRepository
import com.ayustark.itunes.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val repository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}