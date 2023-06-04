package com.hy0417sage.mediastorage.data.model.search

/**
 * api return 값의 형태
 */

data class ResultSearchImage(
    val meta: ImageMeta,
    val documents: List<ImageDocuments>,
)

data class ImageMeta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean,
)

data class ImageDocuments(
    val collection: String,
    val thumbnail_url: String,
    val image_url: String,
    val width: Int,
    val height: Int,
    val display_sitename: String,
    val doc_url: String,
    val datetime: String,
)
