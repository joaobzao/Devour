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

    /* Dummy data*/
    val francesinha = MenuItem(
        name = "Francesinha",
        type = listOf("À Porto carago", "picante no ponto"),
        images = listOf("https://media-cdn.tripadvisor.com/media/photo-s/08/77/5e/6a/photo0jpg.jpg"),
        alternateName = listOf("sandes", "sandwish", "tosta"),
        description = "Iguaria que nasceu no Porto mas que se encontra por todo o norte de Portugal. Uma espécie de sande com carnes, enchidos, queijos e um molho especial que é diferente de restaurante para restaurante.",
        rateStar = 5
    )

    val francesinha1 = MenuItem(
        name = "Francesinha",
        type = listOf("À Porto carago", "picante no ponto"),
        images = listOf("https://media-cdn.tripadvisor.com/media/photo-s/06/ca/2c/83/bon-appetit.jpg"),
        alternateName = listOf("sandes", "sandwish", "tosta"),
        description = "Iguaria que nasceu no Porto mas que se encontra por todo o norte de Portugal. Uma espécie de sande com carnes, enchidos, queijos e um molho especial que é diferente de restaurante para restaurante.",
        rateStar = 4
    )

    val santiago = Restaurant(
        name = "Café Santiago",
        description = "A mais conhecida, de longe.",
        images = listOf("https://cafesantiago.pt/images/2019/11/12/francesinha16.jpg"),
        servesCuisine = listOf("Portuguesa", "Nortenha"),
        bestMenuItem = "francesinha",
        hasMenuItem = francesinha1)

    val francesinhaCafe = Restaurant(
        name = "Francesinha Café",
        description = "A melhor de todas. Especializado em francesinhas, com ingredientes e qualidade e com vários trofeus ganhos em competições de gastronómicas.",
        images = listOf("https://b.zmtcdn.com/data/pictures/6/17835186/763bc7d2da2138ab94c7ab7dfa2069b3.jpg"),
        servesCuisine = listOf("Portuguesa", "Nortenha"),
        bestMenuItem = "francesinha",
        hasMenuItem = francesinha)

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