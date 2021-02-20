package com.ayustark.itunes.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SearchEntity::class],version = 1)
abstract class SearchDatabase: RoomDatabase() {

    abstract fun searchDao(): SearchDao

}