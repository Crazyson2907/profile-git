package com.example.profilegit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.profilegit.presentation.Screen
import com.example.profilegit.presentation.user_details.UserDetailsScreen
import com.example.profilegit.presentation.user_list.UserListScreen
import com.example.profilegit.ui.theme.ProfileGitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileGitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.UserListScreen.route
                    ) {
                        composable(
                            route = Screen.UserListScreen.route
                        ) {
                            UserListScreen(navController)
                        }
                        composable(
                            route = Screen.UserDetailsScreen.route + "/{login}"
                        ) {
                            UserDetailsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}