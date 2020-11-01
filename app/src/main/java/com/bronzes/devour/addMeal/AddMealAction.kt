package com.bronzes.devour.addMeal

import com.bronzes.devour.data.Restaurant

sealed class AddMealAction {
    data class SubmitMeal(val restaurant: Restaurant) : AddMealAction()
}