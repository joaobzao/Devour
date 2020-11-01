package com.bronzes.devour.addMeal

import com.bronzes.devour.data.Restaurant
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddMealRepository @Inject constructor() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun addMeal(restaurant: Restaurant) {
        firestore
            .collection("Restaurant")
            .add(restaurant)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error adding document $e")
            }
            .await()
    }
}