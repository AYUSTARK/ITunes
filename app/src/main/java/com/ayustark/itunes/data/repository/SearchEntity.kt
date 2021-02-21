package com.ayustark.itunes.data.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ayustark.itunes.data.model.Result

@Entity(tableName = "SongSearch")
data class SearchEntity(
    @ColumnInfo(name = "searchQuery")
    val searchQuery: String,
    @ColumnInfo(name = "songs")
    val songs: List<Result>
)