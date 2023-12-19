package com.example.profilegit.domain.cache.usecase

import com.example.profilegit.domain.cache.core.UserCacheRepository
import com.example.profilegit.domain.core.model.User

interface GetUsersFromCacheUseCase {

    suspend fun execute(): List<User>

    suspend fun executeSingle(id: Int): User

    class Base(
        private val repository: UserCacheRepository
    ) : GetUsersFromCacheUseCase {
        override suspend fun execute(): List<User> {
            return repository.getUsersList()
        }

        override suspend fun executeSingle(id: Int): User {
            return repository.getOneUserFromCache(id)
        }
    }
}