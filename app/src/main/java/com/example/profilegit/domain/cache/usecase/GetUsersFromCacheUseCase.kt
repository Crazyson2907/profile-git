package com.example.profilegit.domain.cache.usecase

import com.example.profilegit.domain.cache.core.UserCacheRepository
import com.example.profilegit.domain.core.model.User

interface GetUsersFromCacheUseCase {

    suspend fun execute(): List<User>

    class Base(
        private val repository: UserCacheRepository
    ) : GetUsersFromCacheUseCase {
        override suspend fun execute(): List<User> {
            return repository.getUsersList()
        }
    }
}