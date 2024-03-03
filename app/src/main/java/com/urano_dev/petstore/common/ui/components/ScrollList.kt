package com.urano_dev.petstore.common.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> InfiniteScrollList(
    listItems: List<T>,
    loadMore: () -> Unit,
    buffer: Int = 1,
    modifier: Modifier= Modifier,
    itemBuilder: @Composable (item: T) -> Unit,
) {
    val listState = rememberLazyListState()

    // observe list scrolling
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0
                    && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
        }
    }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom) loadMore()
    }

    // display our list
    LazyColumn(state = listState) {
        items(listItems) { item ->
            itemBuilder.invoke(item)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}