package com.hy0417sage.mediastorage.search.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hy0417sage.core.ui.CoilImageView
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.search.viewdata.SearchResultViewData
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun SearchResultComponentView(
    modifier: Modifier = Modifier,
    viewData: SearchResultViewData,
) {
    val isBookmark by viewData.isBookMark.collectAsState()

    Row(
        modifier = modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CoilImageView(
            modifier = Modifier
                .aspectRatio(1f),
            imageUrl = viewData.imageUrl,
        )

        Text(
            modifier = Modifier
                .weight(1f),
            text = viewData.createDate,
        )

        Icon(
            painter = painterResource(R.drawable.dobookmarks),
            tint = if (isBookmark) Color.Black else Color.LightGray,
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun PreviewSearchResultComponentView(){
    val isBookMark = MutableStateFlow(false)

    Surface {
        SearchResultComponentView(
            viewData = SearchResultViewData(
                imageUrl = "",
                createDate = "2025년 4월 8일",
                isBookMark = isBookMark,
            )
        )
    }
}