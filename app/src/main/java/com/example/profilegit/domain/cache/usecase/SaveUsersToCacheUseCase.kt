package com.example.profilegit.domain.cache.usecase

import com.example.profilegit.domain.cache.core.UserCacheRepository
import com.example.profilegit.domain.core.model.User

interface SaveUsersToCacheUseCase {

    suspend fun execute(users: List<User>)

    class Base(
        private val repository: UserCacheRepository
    ) : SaveUsersToCacheUseCase {
        override suspend fun execute(users: List<User>) {
            repository.saveUsersToCache(users)
        }

    }
}