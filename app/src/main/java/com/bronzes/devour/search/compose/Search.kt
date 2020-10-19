package com.bronzes.devour.search.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bronzes.devour.R

@Composable
fun Search() {
    Box(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
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
                    //actioner(SearchAction.Search(it.text))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
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