package com.ayustark.itunes.data.repository

import androidx.room.Entity

@Entity(tableName = "SearchResult")
data class SearchEntity(
    val artistID: Int
)