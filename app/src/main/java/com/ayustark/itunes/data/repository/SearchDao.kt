package com.ayustark.itunes.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(searchResult: SearchEntity): Long

    @Query("Select * from SongSearch where searchQuery = :search")
    suspend fun searchSongs(search: String): List<SearchEntity>

    @Query("delete from SongSearch")
    suspend fun clearSongs()
}