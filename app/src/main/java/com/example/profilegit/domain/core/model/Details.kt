package com.example.profilegit.domain.core.model

import com.example.profilegit.domain.cache.entity.DetailsEntity

data class Details(
    val avatarUrl: String,
    val id: Int = 0,
    val location: String?,
    val login: String,
    val name: String,
    val isFavorite: Boolean? = false
) {
    fun toDetailsEntity(): DetailsEntity =
        DetailsEntity(
            id = id,
            avatarUrl = avatarUrl,
            location = location,
            login = login,
            name = name,
            isFavorite = isFavorite
        )
}