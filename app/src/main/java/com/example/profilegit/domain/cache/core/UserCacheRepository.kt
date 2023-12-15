package com.example.profilegit.domain.cache.core

import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.domain.core.model.User

interface UserCacheRepository {

    suspend fun getUsersList(): List<User>

    suspend fun saveUsersToCache(users: List<User>)

    suspend fun getDetails(login: String): Details

    suspend fun saveDetailsToCache(details: Details)
}