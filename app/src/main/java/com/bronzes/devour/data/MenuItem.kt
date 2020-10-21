package com.bronzes.devour.data

data class MenuItem(
    val id: Long,
    val name: String,
    val description: String?,
    val images: List<String>,
)