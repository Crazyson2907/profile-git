package com.example.profilegit.presentation.user_list

import com.example.profilegit.domain.core.model.User

sealed class UserListState {
    object Loading : UserListState()

    data class ListSuccessfullyFetched(
        val list: List<User>,
        val isSorted: Boolean = false
    ) : UserListState()

    object ErrorOccurred : UserListState()
}