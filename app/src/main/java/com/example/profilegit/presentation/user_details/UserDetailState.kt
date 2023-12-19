package com.example.profilegit.presentation.user_details

import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.domain.core.model.User

sealed class UserDetailState {
    object Loading : UserDetailState()

    data class DetailsSuccessfullyFetched(
        val details: Details
    ) : UserDetailState()

    data class UserSuccessfullyFetched(
        val user: User
    ): UserDetailState()

    object ErrorOccurred : UserDetailState()
}