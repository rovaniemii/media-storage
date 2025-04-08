package com.hy0417sage.core.ui

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CoilImageView(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    AsyncImage(
        modifier = modifier
            .background(
                color = Color.Gray,
            ),
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}