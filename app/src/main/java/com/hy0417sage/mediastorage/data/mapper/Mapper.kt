package com.hy0417sage.mediastorage.data.mapper

import com.hy0417sage.mediastorage.data.model.ImageDocuments
import com.hy0417sage.mediastorage.data.model.VClipDocuments
import com.hy0417sage.mediastorage.domain.model.ViewData

fun imageMapper(imageData: List<ImageDocuments>): List<ViewData> {
    return imageData.sortedByDescending { it.datetime }.map {
        ViewData(
            it.thumbnail_url,
            it.datetime,
        )
    }
}

fun vClipMapper(vClipData: List<VClipDocuments>): List<ViewData> {
    return vClipData.sortedByDescending { it.datetime }.map {
        ViewData(
            it.thumbnail,
            it.datetime,
        )
    }
}
