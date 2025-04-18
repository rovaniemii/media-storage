package com.hy0417sage.mediastorage.search.view.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputView(
    modifier: Modifier = Modifier,
    initValue: String,
    hint: String,
    onValueChange: (changedValue: String) -> Unit,
    iconContent: @Composable RowScope.(isFocused: Boolean) -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isFocused) {
            Color.Black
        } else {
            Color.Gray
        },
        label = "backgroundColor",
    )

    val borderColor by animateColorAsState(
        targetValue = if (isFocused) {
            Color.Black
        } else {
            Color.Gray
        },
        label = "borderColor",
    )

    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(
                minHeight = 48.dp
            )
            .onFocusChanged { focus ->
                isFocused = focus.isFocused
            },
        value = initValue,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(12.dp),
                    )
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(12.dp),
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    if (initValue.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterStart),
                            text = hint,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    innerTextField()
                }

                iconContent(isFocused)
            }
        }
    )
}

@Preview
@Composable
private fun PreviewSearchWordInputView() {
    var initValue by remember { mutableStateOf("") }

    Surface {
        InputView(
            initValue = initValue,
            hint = "입력해주세요.",
            onValueChange = {
                initValue = it
            },
        )
    }
}