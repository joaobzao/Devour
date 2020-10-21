package com.bronzes.devour.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.onSizeChanged
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bronzes.devour.R
import com.bronzes.devour.compose.WorkaroundLazyColumnFor
import com.bronzes.devour.data.MenuItem

@Composable
fun Search(
    state: SearchViewState,
    action: (SearchAction) -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        val searchBarHeight = remember { mutableStateOf(0) }

        SearchList(
            results = state.results,
            contentPadding = PaddingValues(
                top = with(DensityAmbient.current) { searchBarHeight.value.toDp() }
            ),
            onShowClicked = { action(SearchAction.OpenMenuItemDetails(it.id)) }
        )

        Box(
            modifier = Modifier
                .onSizeChanged { searchBarHeight.value = it.height }
                .background(MaterialTheme.colors.surface.copy(alpha = 0.95f))
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            val searchQuery = savedInstanceState(saver = TextFieldValue.Saver) {
                TextFieldValue("")
            }

            SearchTextField(
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                    action(SearchAction.Search(it.text))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun SearchList(
    results: List<MenuItem>,
    onShowClicked: (MenuItem) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    WorkaroundLazyColumnFor(
        items = results,
        contentPadding = contentPadding
    ) { result ->
        SearchRow(
            menuItem = result,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onShowClicked(result) }
        )
    }
}

@Composable
private fun SearchRow(
    menuItem: MenuItem,
    modifier: Modifier = Modifier
) {
    Row(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {

        Spacer(Modifier.preferredWidth(16.dp))

        Column(Modifier.weight(1f).align(Alignment.CenterVertically)) {
            ProvideEmphasis(AmbientEmphasisLevels.current.high) {
                Text(
                    text = menuItem.name,
                    style = MaterialTheme.typography.subtitle1,
                )
            }

            if (menuItem.description?.isNotEmpty() == true) {
                ProvideEmphasis(AmbientEmphasisLevels.current.medium) {
                    Text(
                        text = menuItem.description,
                        style = MaterialTheme.typography.caption,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SearchTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            ProvideEmphasis(AmbientEmphasisLevels.current.medium) {
                Text(text = stringResource(R.string.search_hint))
            }
        },
        imeAction = ImeAction.Search,
        onImeActionPerformed = { _, keyboardController ->
            keyboardController?.hideSoftwareKeyboard()
        },
        leadingIcon = {
            Icon(asset = Icons.Default.Search)
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = value.text.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                IconButton(
                    onClick = { onValueChange(TextFieldValue()) },
                    icon = { Icon(asset = Icons.Default.Clear) }
                )
            }
        },
        modifier = modifier
    )
}