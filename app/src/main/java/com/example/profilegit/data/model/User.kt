package com.example.profilegit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val avatarUrl: String,
    val login: String
)
