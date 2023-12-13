package com.example.profilegit.presentation

sealed class Screen(val route: String) {

    object UserListScreen: Screen("user_list_screen")

    object UserDetailsScreen: Screen("user_detail_screen")
}
