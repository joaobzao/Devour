package com.bronzes.devour.data

import com.google.firebase.firestore.DocumentId

data class MenuItem(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val type: List<String> = emptyList(),
    val description: String? = "",
    val rateStar: Int = 0,
    val alternateName: List<String>? = emptyList(),
    val images: List<String> = emptyList(),
)

data class Restaurant(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val description: String? = "",
    val images: List<String> = emptyList(),
    val servesCuisine: List<String> = emptyList(),
    val menuItemNames: List<String> = emptyList(),
    val hasMenuItem: List<MenuItem> = emptyList()
)
