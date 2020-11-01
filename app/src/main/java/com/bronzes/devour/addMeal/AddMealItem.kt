package com.bronzes.devour.addMeal

import com.bronzes.devour.common.AppCoroutineDispatchers
import com.bronzes.devour.common.SuspendingWorkInteractor
import com.bronzes.devour.data.Restaurant
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddMealItem @Inject constructor(
    private val addMealRepository: AddMealRepository,
    private val dispatchers: AppCoroutineDispatchers
) : SuspendingWorkInteractor<AddMealItem.Params, Unit>() {
    override suspend fun doWork(params: Params) {
        return withContext(dispatchers.io) {
            addMealRepository.addMeal(params.restaurant)
        }
    }

    data class Params(val restaurant: Restaurant)
}