package com.hy0417sage.mediastorage.data.mapper

import com.hy0417sage.mediastorage.data.model.search.ImageDocuments
import com.hy0417sage.mediastorage.data.model.search.VClipDocuments
import com.hy0417sage.mediastorage.domain.model.ViewData

fun totalMapper(totalData: List<ViewData>) = totalData.sortedByDescending { it.datetime }

fun imageMapper(imageData: List<ImageDocuments>): List<ViewData> {
    return imageData.toList().map {
        ViewData(
            it.thumbnail_url,
            it.datetime,
            false,
            "",
            "image",
        )
    }
}

fun vClipMapper(vClipData: List<VClipDocuments>): List<ViewData> {
    return vClipData.toList().map {
        ViewData(
            it.thumbnail,
            it.datetime,
            false,
            "",
            "vClip",
        )
    }
}