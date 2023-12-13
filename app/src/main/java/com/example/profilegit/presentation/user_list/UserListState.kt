package com.example.profilegit.presentation.user_list

import com.example.profilegit.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)