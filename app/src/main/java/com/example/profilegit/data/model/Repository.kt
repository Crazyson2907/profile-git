package com.example.profilegit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repository(
    @PrimaryKey
    val id: Int,
    val name: String,
    val language: String?,
)