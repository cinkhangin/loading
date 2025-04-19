package com.naulian.loading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naulian.loading.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {
                    composable<Home> {
                        HomeScreen(navController)
                    }
                    composable<Second> {
                        SecondScreen(navController)
                    }
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
object Second

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            navController.navigate(Second)
        }) {
            Text("TO Second")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text("Back Home")
        }
    }
}