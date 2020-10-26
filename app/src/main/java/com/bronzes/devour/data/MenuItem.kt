package com.bronzes.devour.data

import com.google.firebase.firestore.DocumentId

data class MenuItem(
    val name: String = "default",
    val type: List<String> = emptyList(),
    val description: String? = "default",
    val rateStar: Int = 0,
    val alternateName: List<String>? = emptyList(),
    val images: List<String> = emptyList(),
)

data class Restaurant(
    @DocumentId
    val id: String = "0",
    val name: String = "default",
    val description: String? = "default",
    val images: List<String> = emptyList(),
    val servesCuisine: List<String> = emptyList(),
    val bestMenuItem: String = "default",
    val hasMenuItem: MenuItem = MenuItem()
)
