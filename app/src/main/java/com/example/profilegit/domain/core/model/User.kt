package com.example.profilegit.domain.core.model

import com.example.profilegit.domain.cache.entity.UserEntity

data class User(
    val avatarUrl: String,
    val id: Int = 0,
    val login: String,
    val isFavorite: Boolean = false
) {
    fun toUserEntity(): UserEntity =
        UserEntity(
            id = id,
            avatarUrl = avatarUrl,
            login = login,
            isFavorite = isFavorite
        )
}