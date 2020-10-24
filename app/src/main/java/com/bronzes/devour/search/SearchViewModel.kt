package com.bronzes.devour.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.bronzes.devour.common.ReduxViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val searchMenuItems: SearchMenuItems
) : ReduxViewModel<SearchViewState>(
    SearchViewState()
) {
    private val searchQuery = MutableStateFlow("")
    private val pendingActions = Channel<SearchAction>(Channel.BUFFERED)
    
    init {
        viewModelScope.launch {
            searchQuery.debounce(300)
                .collectLatest { query ->
                    val job = launch {
                        println("🦠 searching for $query")
                        searchMenuItems(SearchMenuItems.Params(query))
                    }
                    job.invokeOnCompletion { println("🦠 on searching completion") }
                    job.join()
                }
        }

        viewModelScope.launch {
            searchMenuItems.observe().collectAndSetState { copy(results = it) }
        }

        viewModelScope.launch {
            pendingActions.consumeAsFlow().collect { action ->
                when (action) {
                    is SearchAction.Search -> {
                        searchQuery.value = action.searchTerm
                    }
                }
            }
        }
    }

    fun submitAction(action: SearchAction) {
        viewModelScope.launch {
            if (!pendingActions.isClosedForSend) {
                pendingActions.send(action)
            }
        }
    }
}