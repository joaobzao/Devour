package com.bronzes.devour.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Provides a workaround for https://issuetracker.google.com/167913500
 */
@OptIn(ExperimentalLazyDsl::class)
@Composable
fun <T> WorkaroundLazyColumnFor(
    items: List<T>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    itemContent: @Composable LazyItemScope.(T) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding.copy(top = 0.dp, bottom = 0.dp),
        horizontalAlignment = horizontalAlignment
    ) {
        if (contentPadding.top > 0.dp) {
            item { Spacer(Modifier.preferredHeight(contentPadding.top)) }
        }

        items(items, itemContent)

        if (contentPadding.bottom > 0.dp) {
            item { Spacer(Modifier.preferredHeight(contentPadding.bottom)) }
        }
    }
}