package com.hy0417sage.mediastorage.data.model

data class ResultSearchVClip(
    val meta: VideoMeta,
    val documents: List<VClipDocuments>,
)

data class VideoMeta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean,
)

data class VClipDocuments(
    val title: String,
    val play_time: Int,
    val thumbnail: String,
    val url: String,
    val datetime: String,
    val author: String,
)