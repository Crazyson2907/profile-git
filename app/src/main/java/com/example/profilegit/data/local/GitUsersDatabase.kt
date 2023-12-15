package com.example.profilegit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.profilegit.domain.cache.entity.DetailsEntity
import com.example.profilegit.domain.cache.entity.UserEntity

@Database(version = 1, entities = [UserEntity::class, DetailsEntity::class], exportSchema = false)
abstract class GitUsersDatabase : RoomDatabase() {
    abstract fun gitUsersDao(): GitUsersDao
}