package com.bronzes.devour

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import androidx.ui.tooling.preview.Preview
import com.bronzes.devour.authentication.AuthActivity
import com.bronzes.devour.authentication.User
import com.bronzes.devour.meal.MealActivity
import com.bronzes.devour.search.Search
import com.bronzes.devour.search.SearchAction
import com.bronzes.devour.search.SearchViewModel
import com.bronzes.devour.ui.DevourTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel

@AndroidEntryPoint
class Home : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModels()
    private val pendingActions = Channel<SearchAction>(Channel.BUFFERED)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.getParcelableExtra<User>(AuthActivity.USER)

        Toast.makeText(this, user?.name, Toast.LENGTH_SHORT).show()

        lifecycleScope.launchWhenStarted {
            for (action in pendingActions) {
                println("🦠 action -> $action")
                when (action) {
                    is SearchAction.OpenRestaurantDetails -> {
                        val message = "🦠 is going to navigate to MenuItemDetails with action -> $action"
                        Toast.makeText(this@Home, message, Toast.LENGTH_SHORT).show()
                    }
                    is SearchAction.AddSuperMeal -> {
                        goToMealActivity()
                    }
                    else -> viewModel.submitAction(action)
                }
            }
        }

        setContent {
            DevourTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    user?.let { Greeting(it) }
                }
            }
        }
    }

    private fun goToMealActivity() {
        val intent = Intent(this, MealActivity::class.java)
        startActivity(intent)
    }

    @Preview
    @Composable
    fun Greeting(user: User = User("", "batatas", "")) {
        val viewState by viewModel.liveData.observeAsState()
        DevourTheme {
            Column(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewState?.let {
                    Search(it) { action ->
                        pendingActions.offer(action)
                    }
                }
            }
        }
    }
}