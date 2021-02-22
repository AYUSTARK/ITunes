package com.ayustark.itunes.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SearchEntity::class], version = 1)
abstract class SearchDatabase : RoomDatabase() {

    abstract val searchDao: SearchDao

    companion object { //in kotlin we use 'companion object' as singleton
        @Volatile //@Volatile makes the field immediately visible to other threads
        private var INSTANCE: SearchDatabase? = null
        fun getInstance(context: Context): SearchDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SearchDatabase::class.java,
                        "Songs_Database"
                    ).build()
                }
                return instance
            }
        }
    }

}