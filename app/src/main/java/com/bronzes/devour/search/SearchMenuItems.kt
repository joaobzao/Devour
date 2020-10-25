package com.bronzes.devour.search

import com.bronzes.devour.common.AppCoroutineDispatchers
import com.bronzes.devour.common.SuspendingWorkInteractor
import com.bronzes.devour.data.MenuItem
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchMenuItems @Inject constructor(
    private val searchRepository: SearchRepository,
    private val dispatchers: AppCoroutineDispatchers
) : SuspendingWorkInteractor<SearchMenuItems.Params, List<MenuItem>>() {
    override suspend fun doWork(params: Params): List<MenuItem> {
        return withContext(dispatchers.io) {
            val remoteResults = searchRepository.fetchMenuItems(params.query)
            if (!remoteResults.isNullOrEmpty()) {
                remoteResults
            } else {
                emptyList()
            }
        }
    }

    data class Params(val query: String)
}