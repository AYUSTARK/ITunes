package com.ayustark.itunes.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(searchResult: SearchEntity): Long

    @Query("Select * from SongSearch where searchQuery = :search")
    fun searchSongs(search: String): LiveData<List<SearchEntity>>

    @Query("delete from SongSearch")
    suspend fun clearSongs()
}