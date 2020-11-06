package com.bronzes.devour.search

import com.bronzes.devour.data.MenuItem
import com.bronzes.devour.data.Restaurant
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun search(query: String): List<Restaurant>? {
        return try {
            val data = firestore
                .collection("Restaurant")
                .whereGreaterThanOrEqualTo("bestMenuItem", query)
                .whereLessThanOrEqualTo("bestMenuItem", "$query~")
                .get()
                .await()

                val restaurants = mutableListOf<Restaurant>()

                for (document in  data) {
                    val restaurantItem = document.toObject(Restaurant::class.java)
                    restaurants.add(restaurantItem)
                }
                println("🦠 restaurants => $restaurants}")

            restaurants.sortedByDescending { it.hasMenuItem.rateStar }

        } catch (e: Exception) {
            null
        }
    }
}