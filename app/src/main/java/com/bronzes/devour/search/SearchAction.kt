package com.bronzes.devour.search

sealed class SearchAction {
    data class OpenMenuItemDetails(val menuItemId: String) : SearchAction()
    data class Search(val searchTerm: String = "") : SearchAction()
}