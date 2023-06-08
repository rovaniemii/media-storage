package com.hy0417sage.data.remote.mapper

import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.data.remote.model.ImageDocuments
import com.hy0417sage.data.remote.model.VClipDocuments

fun totalMapper(totalData: List<SearchItem>) = totalData.sortedByDescending { it.datetime }

fun imageMapper(imageData: List<ImageDocuments>): List<SearchItem> {
    return imageData.toList().map {
        SearchItem(
            it.thumbnail_url,
            it.datetime,
            false,
            "",
        )
    }
}

fun vClipMapper(vClipData: List<VClipDocuments>): List<SearchItem> {
    return vClipData.toList().map {
        SearchItem(
            it.thumbnail,
            it.datetime,
            false,
            "",
        )
    }
}