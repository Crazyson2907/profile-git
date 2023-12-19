package com.example.profilegit.presentation.user_list

import com.example.profilegit.domain.core.model.User

/**
 * All possible states during the fetch of the list
 */
sealed class UserListState {
    object Loading : UserListState()

    data class ListSuccessfullyFetched(
        val list: List<User>,
        val isSorted: Boolean = false
    ) : UserListState()

    object ErrorOccurred : UserListState()
}