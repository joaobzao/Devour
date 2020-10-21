package com.bronzes.devour.search

import com.bronzes.devour.data.MenuItem

data class SearchViewState(
    val query: String = "",
    val results: List<MenuItem> = emptyList(),
    val refreshing: Boolean = false
)