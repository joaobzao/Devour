package com.bronzes.devour.addMeal

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import androidx.ui.tooling.preview.Preview
import com.bronzes.devour.search.SearchAction
import com.bronzes.devour.ui.DevourTheme
import kotlinx.coroutines.channels.Channel

private val pendingActions = Channel<AddMealAction>(Channel.BUFFERED)

class AddMealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            for (action in pendingActions) {
                println("🦠 action -> $action")
                when (action) {
                    is AddMealAction.SubmitMeal -> {
                        Toast.makeText(this@AddMealActivity, action.restaurant.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        setContent {
            DevourTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AddFormContainer()
                }
            }
        }
    }
}

@Composable
fun AddFormContainer() {
    Column(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddMeal {
            pendingActions.offer(it)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    DevourTheme {
        AddFormContainer()
    }
}