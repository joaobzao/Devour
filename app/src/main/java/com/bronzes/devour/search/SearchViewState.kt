package com.bronzes.devour.search

import com.bronzes.devour.data.Restaurant

data class SearchViewState(
    val query: String = "",
    val results: List<Restaurant> = emptyList(),
    val refreshing: Boolean = false
)