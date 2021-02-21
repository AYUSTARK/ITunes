package com.ayustark.itunes.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SearchDao {
    @Insert
    fun insertSongs(searchResult: SearchEntity)

    @Query("Select * from SongSearch where searchQuery like :search")
    fun searchSongs(search: String): List<SearchEntity>


}