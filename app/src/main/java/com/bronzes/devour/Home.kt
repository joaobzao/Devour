package com.bronzes.devour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.bronzes.devour.authentication.AuthActivity
import com.bronzes.devour.authentication.User
import com.bronzes.devour.search.compose.Search
import com.bronzes.devour.ui.DevourTheme

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevourTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    intent.getParcelableExtra<User>(AuthActivity.USER)?.let { Greeting(it) }
                }
            }
        }
    }
}

@Preview
@Composable
fun Greeting(user: User = User("", "batatas", "")) {
    DevourTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Search()
            Text(text = "Hello ${user.name}!")
        }
    }
}