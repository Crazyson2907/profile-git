package com.example.profilegit.data.model

import androidx.room.PrimaryKey

data class Details(
    @PrimaryKey
    val id: Int,
    val avatarUrl: String,
    val login: String,
    val name: String,
    val location: String
)
