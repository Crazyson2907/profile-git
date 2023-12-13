package com.example.profilegit.presentation.user_details

import com.example.profilegit.domain.model.Details

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: Details? = null,
    val error: String = ""
)