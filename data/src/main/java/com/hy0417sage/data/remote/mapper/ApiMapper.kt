package com.hy0417sage.data.remote.mapper

import android.content.Context
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.core.util.isBookMarked
import com.hy0417sage.data.remote.model.ImagesDTO
import com.hy0417sage.data.remote.model.VideoDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ApiMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun imageToSearchItem(apiEntity: ImagesDTO?): SearchItem {
        return SearchItem(
            imageUrl = apiEntity?.thumbnailUrl.orEmpty(),
            datetime = apiEntity?.dateTime.orEmpty(),
            bookmark = context.isBookMarked(apiEntity?.thumbnailUrl.orEmpty())
        )
    }

    fun videoToSearchItem(apiEntity: VideoDTO?): SearchItem {
        return SearchItem(
            imageUrl = apiEntity?.thumbnail.orEmpty(),
            datetime = apiEntity?.dateTime.orEmpty(),
            bookmark = context.isBookMarked(apiEntity?.thumbnail.orEmpty())
        )
    }
}