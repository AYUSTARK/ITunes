package com.ayustark.itunes.data.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SongSearch")
data class SearchEntity(
    @ColumnInfo(name = "searchQuery")
    var searchQuery: String?,
    @SerializedName("collectionId")
    val collectionId: Int,
    @PrimaryKey
    @SerializedName("trackId")
    val trackId: Int,
    @SerializedName("trackNumber")
    val trackNumber: Int,
    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("artistId")
    val artistId: Int,
    @SerializedName("collectionName")
    val collectionName: String?,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String?,
    @SerializedName("artworkUrl600")
    val artworkUrl600: String?,
    @SerializedName("trackPrice")
    val trackPrice: Double,
)