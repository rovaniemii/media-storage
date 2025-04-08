package com.hy0417sage.mediastorage.main.search.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.main.search.view.input.InputView

@Composable
internal fun SearchWordInputView(
    modifier: Modifier = Modifier,
    initValue: String,
    onValueChange: (changedValue: String) -> Unit,
) {
    val isShowButton by remember(initValue) { mutableStateOf(initValue.isNotEmpty()) }

    InputView(
        modifier = modifier,
        initValue = initValue,
        hint = "검색어를 입력해주세요.",
        onValueChange = onValueChange,
        iconContent = {
            Spacer(modifier = Modifier.width(8.dp))

            if (isShowButton) {
                Image(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onValueChange("")
                            },
                        ),
                    painter = painterResource(R.drawable.icon_clear),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(20.dp))
            }

            Image(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = null
            )
        },
    )
}

@Preview
@Composable
private fun PreviewSearchWordInputView() {
    Surface {
        var initValue by remember { mutableStateOf("") }

        SearchWordInputView(
            initValue = initValue,
            onValueChange = {
                initValue = it
            },
        )
    }
}