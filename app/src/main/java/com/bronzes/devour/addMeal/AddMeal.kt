package com.bronzes.devour.addMeal

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bronzes.devour.data.MenuItem
import com.bronzes.devour.data.Restaurant

@Composable
fun AddMeal(action: (AddMealAction) -> Unit) {
    Box(Modifier.fillMaxSize()) {

        val mealName = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue("") }
        val restaurantName = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue("") }
        val location = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue("") }
        val type = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue("") }
        val rate = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue("") }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.preferredHeight(44.dp))
            MealOutlinedTextField(mealName, "Meal name")
            Spacer(modifier = Modifier.preferredHeight(16.dp))
            MealOutlinedTextField(restaurantName, "Restaurant name")
            Spacer(modifier = Modifier.preferredHeight(16.dp))
            MealOutlinedTextField(location, "Location")
            Spacer(modifier = Modifier.preferredHeight(16.dp))
            MealOutlinedTextField(rate, "Rate")
            Spacer(modifier = Modifier.preferredHeight(16.dp))
            MealType(meals = listOf("francesinha", "robalo", "cachorrinhos", "cabidela"))
        }

        Spacer(modifier = Modifier.preferredHeight(44.dp))

        Column(Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
            Button(
                enabled = true,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                onClick = {
                    val restaurant = createRestaurant(mealName, restaurantName, type, rate)
                    action(AddMealAction.SubmitMeal(restaurant))
                }
            ) {
                Text("Add Meal!")
            }
        }
    }
}

@Composable
fun MealType(meals: List<String> = emptyList()) {
    val showMenu = remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableStateOf(0) }
    val mealToggle = @Composable {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .clickable(onClick = { showMenu.value = true }),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.Fastfood)
            Spacer(modifier = Modifier.preferredWidth(6.dp))
            Text(meals[selectedIndex.value])
            Spacer(modifier = Modifier.preferredWidth(6.dp))
            Icon(Icons.Default.ArrowDropDown)
        }

    }

    DropdownMenu(
        toggle = mealToggle,
        expanded = showMenu.value,
        onDismissRequest = { showMenu.value = false },
        toggleModifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface),
        dropdownModifier = Modifier.fillMaxWidth().background(Color.DarkGray)
    ) {
        meals.forEachIndexed { index, meal ->
            DropdownMenuItem(
                onClick = {
                    selectedIndex.value = index
                    showMenu.value = false
                }
            ) {
                Text(text = meal)
            }
        }
    }
}

private fun createRestaurant(
    mealName: MutableState<TextFieldValue>,
    restaurantName: MutableState<TextFieldValue>,
    type: MutableState<TextFieldValue>,
    rate: MutableState<TextFieldValue>
) : Restaurant {
    val menuItem = MenuItem(
        name = mealName.value.text,
        rateStar = rate.value.text.toInt()
    )

    val restaurant = Restaurant(
        name = restaurantName.value.text,
        servesCuisine = listOf(type.value.text),
        bestMenuItem = mealName.value.text,
        hasMenuItem = menuItem
    )
    println("Add Meal - $menuItem - from restaurant $restaurant")

    return restaurant
}

@Composable
private fun MealOutlinedTextField(
    mealName: MutableState<TextFieldValue>,
    label: String
) {
    OutlinedTextField(
        value = mealName.value,
        onValueChange = { mealName.value = it },
        label = {
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    )
}