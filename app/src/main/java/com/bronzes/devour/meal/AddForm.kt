package com.bronzes.devour.meal

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun AddForm() {
    Box(Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val mealName = savedInstanceState(saver = TextFieldValue.Saver) {
                TextFieldValue("")
            }
            val restaurantName = savedInstanceState(saver = TextFieldValue.Saver) {
                TextFieldValue("")
            }
            val location = savedInstanceState(saver = TextFieldValue.Saver) {
                TextFieldValue("")
            }
            val type = savedInstanceState(saver = TextFieldValue.Saver) {
                TextFieldValue("")
            }
            val rate = savedInstanceState(saver = TextFieldValue.Saver) {
                TextFieldValue("")
            }

            Spacer(modifier = Modifier.preferredHeight(44.dp))

            OutlinedTextField(
                value = mealName.value,
                onValueChange = { mealName.value = it },
                label = {
                    ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                        Text(
                            text = "Meal name",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            OutlinedTextField(
                value = restaurantName.value,
                onValueChange = { restaurantName.value = it },
                label = {
                    ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                        Text(
                            text = "Restaurant name",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            OutlinedTextField(
                value = location.value,
                onValueChange = { location.value = it },
                label = {
                    ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                        Text(
                            text = "Location",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            OutlinedTextField(
                value = type.value,
                onValueChange = { type.value = it },
                label = {
                    ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                        Text(
                            text = "Type",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            OutlinedTextField(
                value = rate.value,
                onValueChange = { rate.value = it },
                label = {
                    ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                        Text(
                            text = "Rate",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.preferredHeight(44.dp))
        
        Column(Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
            Button(
                enabled = true,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                onClick = { println("Add Meal") }
            ) {
                Text("Add Meal!")
            }
        }
    }
}