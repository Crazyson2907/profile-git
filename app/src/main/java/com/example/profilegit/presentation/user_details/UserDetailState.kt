package com.example.profilegit.presentation.user_details

import com.example.profilegit.domain.core.model.Details

sealed class UserDetailState {
    object Loading : UserDetailState()

    data class DetailsSuccessfullyFetched(
        val user: Details
    ) : UserDetailState()

    object ErrorOccurred : UserDetailState()
}