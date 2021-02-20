package com.ayustark.itunes.data.model

import com.google.gson.annotations.SerializedName

data class ResultApi(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<Result>
)