package com.ayustark.itunes.model

data class ResultApi(
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val artworkUrl600: String,
    val collectionArtistId: Int,
    val collectionArtistName: String,
    val collectionArtistViewUrl: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val collectionHdPrice: Int,
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Int,
    val collectionViewUrl: String,
    val contentAdvisoryRating: String,
    val country: String,
    val currency: String,
    val discCount: Int,
    val discNumber: Int,
    val feedUrl: String,
    val genreIds: List<String>,
    val genres: List<String>,
    val isStreamable: Boolean,
    val kind: String,
    val previewUrl: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCensoredName: String,
    val trackCount: Int,
    val trackExplicitness: String,
    val trackHdPrice: Int,
    val trackHdRentalPrice: Int,
    val trackId: Int,
    val trackName: String,
    val trackNumber: Int,
    val trackPrice: Int,
    val trackRentalPrice: Int,
    val trackTimeMillis: Int,
    val trackViewUrl: String,
    val wrapperType: String
)