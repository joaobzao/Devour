package com.bronzes.devour.search

import com.bronzes.devour.common.AppCoroutineDispatchers
import com.bronzes.devour.common.SuspendingWorkInteractor
import com.bronzes.devour.data.Restaurant
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchMenuItems @Inject constructor(
    private val searchRepository: SearchRepository,
    private val dispatchers: AppCoroutineDispatchers
) : SuspendingWorkInteractor<SearchMenuItems.Params, List<Restaurant>>() {
    override suspend fun doWork(params: Params): List<Restaurant> {
        return withContext(dispatchers.io) {
            val remoteResults = searchRepository.search(params.query)
            if (!remoteResults.isNullOrEmpty()) {
                remoteResults
            } else {
                emptyList()
            }
        }
    }

    data class Params(val query: String)
}