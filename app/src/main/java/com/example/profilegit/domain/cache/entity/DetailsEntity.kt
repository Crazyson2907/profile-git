package com.example.profilegit.domain.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profilegit.domain.core.model.Details

@Entity(tableName = "details_entity")
data class DetailsEntity(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    val location: String?,
    val login: String,
    val name: String,
    val isFavorite: Boolean? = false
) {
    fun toDetails(): Details =
        Details(
            id = id,
            avatarUrl = avatarUrl,
            login = login,
            location = location,
            name = name,
            isFavorite = isFavorite
        )
}
