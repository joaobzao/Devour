package com.bronzes.devour.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ExpandableFloatingActionButton(
    text: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
    backgroundColor: Color = MaterialTheme.colors.secondary,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 2.dp,
    expanded: Boolean = true
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = backgroundColor,
        elevation = elevation,
        contentColor = contentColor,
        shape = shape,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AnimatedVisibility(visible = expanded) {
                Spacer(Modifier.preferredWidth(20.dp))
            }

            icon()

            AnimatedVisibility(visible = expanded) {
                Box(Modifier.padding(start = 12.dp, end = 20.dp)) {
                    text()
                }
            }
        }
    }
}
