package com.bronzes.devour.search

sealed class SearchAction {
    data class OpenRestaurantDetails(val menuItemId: String) : SearchAction()
    data class Search(val searchTerm: String = "") : SearchAction()
    object AddSuperMeal : SearchAction()
}