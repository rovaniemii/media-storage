package com.hy0417sage.mediastorage.data.mapper

import com.hy0417sage.mediastorage.ApplicationClass.Companion.sharedPreference
import com.hy0417sage.mediastorage.data.model.ImageDocuments
import com.hy0417sage.mediastorage.data.model.VClipDocuments
import com.hy0417sage.mediastorage.domain.model.ViewData

fun totalMapper(totalData: List<ViewData>) = totalData.sortedByDescending { it.datetime }

fun imageMapper(imageData: List<ImageDocuments>): List<ViewData> {
    return imageData.toList().map {
        ViewData(
            it.thumbnail_url,
            it.datetime,
            (sharedPreference.getValue(it.thumbnail_url) != null),
            (sharedPreference.getValue(it.thumbnail_url))?.saveTime ?: "", //사실 중요하지 않음
        )
    }
}

fun vClipMapper(vClipData: List<VClipDocuments>): List<ViewData> {
    return vClipData.toList().map {
        ViewData(
            it.thumbnail,
            it.datetime,
            (sharedPreference.getValue(it.thumbnail) != null),
            (sharedPreference.getValue(it.thumbnail))?.saveTime ?: "",
        )
    }
}