package com.bronzes.devour.search

import com.bronzes.devour.data.MenuItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor() {
    fun fetchMenuItems(query: String): List<MenuItem> = listOf(
        MenuItem(1, "Sicario", "dummy description $query", images = listOf("")),
        MenuItem(2, "Gazela", "dummy description $query", images = listOf("")),
        MenuItem(3, "Santiago", "dummy description $query", images = listOf("")),
        MenuItem(4, "ola ola", "dummy description $query", images = listOf("")),
        MenuItem(5, "Sicario", "dummy description $query", images = listOf("")),
        MenuItem(6, "Sicario", "dummy description $query", images = listOf("")),
    )
}