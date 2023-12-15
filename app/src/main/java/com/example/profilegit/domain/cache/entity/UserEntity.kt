package com.example.profilegit.domain.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profilegit.domain.core.model.User

@Entity(tableName = "user_entity")
data class UserEntity(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    val login: String,
    val isFavorite: Boolean = false
) {
    fun toUser(): User =
        User(
            id = id,
            avatarUrl = avatarUrl,
            login = login,
            isFavorite = isFavorite
        )
}
