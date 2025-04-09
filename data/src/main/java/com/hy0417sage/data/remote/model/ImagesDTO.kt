package com.hy0417sage.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesDTO(
    @field:Json(name = "collection") val collection: String?,
    @field:Json(name = "thumbnail_url") val thumbnailUrl: String?,
    @field:Json(name = "image_url") val imageUrl: String?,
    @field:Json(name = "width") val width: Int?,
    @field:Json(name = "height") val height: Int?,
    @field:Json(name = "display_sitename") val displaySiteName: String?,
    @field:Json(name = "doc_url") val docUrl: String?,
    @field:Json(name = "datetime") val dateTime: String,
)