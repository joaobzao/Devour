package com.bronzes.devour.addMeal

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.bronzes.devour.common.ReduxViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class AddMealViewModel @ViewModelInject constructor(
    private val addMealItem: AddMealItem
) : ReduxViewModel<AddMealState>(
    AddMealState()
) {
    private val pendingActions = Channel<AddMealAction>(Channel.BUFFERED)

    init {
        viewModelScope.launch {
            pendingActions.consumeAsFlow().collect { action ->
                when (action) {
                    is AddMealAction.SubmitMeal -> {
                        val job = launch {
                            addMealItem(AddMealItem.Params(action.restaurant))
                        }
                        job.invokeOnCompletion { println("🦠 on add meal item completion") }
                        job.join()
                    }
                }
            }
        }

        viewModelScope.launch {
            addMealItem.observe().collect()
        }
    }

    fun submitAction(action: AddMealAction) {
        viewModelScope.launch {
            if (!pendingActions.isClosedForSend) {
                pendingActions.send(action)
            }
        }
    }
}