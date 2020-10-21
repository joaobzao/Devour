package com.bronzes.devour.search

sealed class SearchAction {
    data class OpenMenuItemDetails(val menuItemId: Long) : SearchAction()
    data class Search(val searchTerm: String = "") : SearchAction()
}