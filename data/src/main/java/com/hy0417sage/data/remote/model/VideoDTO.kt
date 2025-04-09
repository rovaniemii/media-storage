package com.hy0417sage.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoDTO(
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "play_time") val playTime: Int?,
    @field:Json(name = "thumbnail") val thumbnail: String?,
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "datetime") val dateTime: String?,
    @field:Json(name = "author") val author: String?,
)